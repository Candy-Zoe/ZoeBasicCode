/**
 * ZoeBasicCode 在线代码编辑器
 * 自动增强所有 .code-block 元素，添加编辑、复制、运行功能
 */
(function () {
    'use strict';

    // 语言对应的在线编译器链接
    const COMPILERS = {
        python: 'https://www.programiz.com/python-programming/online-compiler/',
        javascript: null, // 内置运行
        typescript: 'https://www.typescriptlang.org/play',
        java: 'https://www.programiz.com/java-programming/online-compiler/',
        c: 'https://www.onlinegdb.com/online_c_compiler',
        cpp: 'https://www.onlinegdb.com/online_c++_compiler',
        csharp: 'https://dotnetfiddle.net/',
        go: 'https://go.dev/play/',
        rust: 'https://play.rust-lang.org/',
        kotlin: 'https://play.kotlinlang.org/',
        shell: 'https://www.onlinegdb.com/online_bash_shell',
        sql: 'https://www.db-fiddle.com/',
        lua: 'https://www.lua.org/demo/',
        r: 'https://rdrr.io/run/',
        css: null // 浏览器内置
    };

    // 语言显示名
    const LANG_NAMES = {
        python: 'Python', javascript: 'JavaScript', typescript: 'TypeScript',
        java: 'Java', c: 'C', cpp: 'C++', csharp: 'C#', go: 'Go',
        rust: 'Rust', kotlin: 'Kotlin', shell: 'Bash', sql: 'SQL',
        lua: 'Lua', r: 'R', css: 'CSS', html: 'HTML', js: 'JavaScript'
    };

    // 创建提示元素
    let toast = null;
    function showToast(msg) {
        if (!toast) {
            toast = document.createElement('div');
            toast.className = 'copy-toast';
            document.body.appendChild(toast);
        }
        toast.textContent = msg;
        toast.classList.add('show');
        clearTimeout(toast._timer);
        toast._timer = setTimeout(() => toast.classList.remove('show'), 2000);
    }

    // 从 code-block 中提取纯代码文本
    function extractCode(block) {
        const codeEl = block.querySelector('code');
        if (!codeEl) return '';
        // 克隆节点并移除 toolbar 相关元素
        const clone = codeEl.cloneNode(true);
        return clone.textContent;
    }

    // 检测语言
    function detectLang(block) {
        if (block.dataset.lang) return block.dataset.lang;
        const langEl = block.querySelector('.code-lang');
        if (langEl) {
            const text = langEl.textContent.toLowerCase().trim();
            for (const key of Object.keys(LANG_NAMES)) {
                if (text.includes(key) || text.includes(LANG_NAMES[key].toLowerCase())) return key;
            }
            return text;
        }
        return 'javascript';
    }

    // 复制到剪贴板
    async function copyCode(text) {
        try {
            await navigator.clipboard.writeText(text);
            showToast('已复制到剪贴板');
        } catch {
            const ta = document.createElement('textarea');
            ta.value = text;
            ta.style.position = 'fixed';
            ta.style.opacity = '0';
            document.body.appendChild(ta);
            ta.select();
            document.execCommand('copy');
            document.body.removeChild(ta);
            showToast('已复制到剪贴板');
        }
    }

    // 运行 JavaScript 代码
    function runJS(code, outputEl) {
        outputEl.innerHTML = '';
        outputEl.classList.remove('error');

        const lines = [];
        const origLog = console.log;
        const origWarn = console.warn;
        const origError = console.error;
        const origInfo = console.info;

        function capture(...args) {
            lines.push(args.map(a => {
                if (typeof a === 'object') {
                    try { return JSON.stringify(a, null, 2); } catch { return String(a); }
                }
                return String(a);
            }).join(' '));
        }

        console.log = capture;
        console.warn = capture;
        console.error = capture;
        console.info = capture;

        try {
            const result = new Function(code)();
            if (result !== undefined && !lines.length) {
                lines.push(String(result));
            }
        } catch (e) {
            outputEl.classList.add('error');
            lines.push('❌ Error: ' + e.message);
        } finally {
            console.log = origLog;
            console.warn = origWarn;
            console.error = origError;
            console.info = origInfo;
        }

        if (lines.length === 0) {
            lines.push('(无输出)');
        }

        outputEl.innerHTML = lines.map(l => {
            const cls = l.startsWith('❌') ? 'output-error' : 'output-result';
            return `<span class="${cls}">${escapeHtml(l)}</span>`;
        }).join('\n');
    }

    function escapeHtml(s) {
        return s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    }

    // 在新窗口运行 CSS
    function runCSS(code) {
        const win = window.open('', '_blank');
        win.document.write(`<!DOCTYPE html><html><head><style>${code}</style></head><body>
            <div class="preview-box"><h1>预览标题</h1><p>这是一个段落文字</p>
            <button>按钮</button><a href="#">链接</a><div class="box">盒子元素</div></div></body></html>`);
    }

    // 在新窗口运行 HTML
    function runHTML(code) {
        const win = window.open('', '_blank');
        win.document.write(code);
    }

    // 在新窗口运行 SQL
    function runSQL(code) {
        const win = window.open('https://www.db-fiddle.com/', '_blank');
        showToast('已打开 SQL 在线编辑器');
    }

    // 在新窗口运行 Shell
    function runShell(code) {
        const win = window.open('https://www.onlinegdb.com/online_bash_shell', '_blank');
        showToast('已打开 Bash 在线编辑器');
    }

    // 处理单个 code-block
    function enhanceBlock(block) {
        if (block.dataset.enhanced) return;
        block.dataset.enhanced = '1';

        const lang = detectLang(block);
        const code = extractCode(block);
        const displayName = LANG_NAMES[lang] || lang;

        // 包装结构
        const wrapper = document.createElement('div');
        wrapper.className = 'editor-wrapper';
        block.parentNode.insertBefore(wrapper, block);

        // 工具栏
        const toolbar = document.createElement('div');
        toolbar.className = 'editor-toolbar';
        toolbar.innerHTML = `
            <div class="dots"><span></span><span></span><span></span></div>
            <span class="code-lang">${escapeHtml(displayName)}</span>
        `;

        // 复制按钮
        const copyBtn = document.createElement('button');
        copyBtn.className = 'editor-btn copy-btn';
        copyBtn.innerHTML = '📋 复制';
        copyBtn.onclick = () => copyCode(code);
        toolbar.appendChild(copyBtn);

        // 运行按钮（JS 内置运行，其他语言在线运行）
        const compilerUrl = COMPILERS[lang];
        if (lang === 'javascript' || lang === 'js') {
            const runBtn = document.createElement('button');
            runBtn.className = 'editor-btn run-btn';
            runBtn.innerHTML = '▶ 运行';
            runBtn.onclick = () => {
                const wrapper = block.closest('.editor-wrapper') || block.parentElement;
                let outputEl = wrapper.querySelector('.editor-output');
                if (!outputEl) {
                    outputEl = document.createElement('div');
                    outputEl.className = 'editor-output';
                    outputEl.innerHTML = `
                        <div class="editor-output-header">
                            <span>输出</span>
                            <button class="close-output" title="关闭">&times;</button>
                        </div>
                        <div class="editor-output-content"></div>
                    `;
                    wrapper.appendChild(outputEl);
                    outputEl.querySelector('.close-output').onclick = () => {
                        outputEl.remove();
                    };
                }
                const content = outputEl.querySelector('.editor-output-content');
                runJS(code, content);
                outputEl.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
            };
            toolbar.appendChild(runBtn);
        } else if (compilerUrl) {
            const onlineBtn = document.createElement('button');
            onlineBtn.className = 'editor-btn run-online-btn';
            onlineBtn.innerHTML = '🚀 在线运行';
            onlineBtn.onclick = () => window.open(compilerUrl, '_blank');
            toolbar.appendChild(onlineBtn);
        } else if (lang === 'css') {
            const runBtn = document.createElement('button');
            runBtn.className = 'editor-btn run-btn';
            runBtn.innerHTML = '▶ 预览';
            runBtn.onclick = () => runCSS(code);
            toolbar.appendChild(runBtn);
        } else if (lang === 'html') {
            const runBtn = document.createElement('button');
            runBtn.className = 'editor-btn run-btn';
            runBtn.innerHTML = '▶ 预览';
            runBtn.onclick = () => runHTML(code);
            toolbar.appendChild(runBtn);
        }

        // 可编辑区域
        const textarea = document.createElement('textarea');
        textarea.className = 'editor-textarea';
        textarea.value = code;
        textarea.spellcheck = false;
        textarea.setAttribute('data-lang', lang);

        // Tab 键支持
        textarea.addEventListener('keydown', (e) => {
            if (e.key === 'Tab') {
                e.preventDefault();
                const start = textarea.selectionStart;
                const end = textarea.selectionEnd;
                textarea.value = textarea.value.substring(0, start) + '    ' + textarea.value.substring(end);
                textarea.selectionStart = textarea.selectionEnd = start + 4;
            }
        });

        // 同步更新 code 块中的代码
        textarea.addEventListener('input', () => {
            const codeEl = block.querySelector('code');
            if (codeEl) codeEl.textContent = textarea.value;
        });

        wrapper.appendChild(toolbar);
        wrapper.appendChild(textarea);

        // 如果原来是 pre > code 结构，隐藏原来的 pre
        if (block.tagName === 'PRE') {
            block.style.display = 'none';
        } else {
            block.style.display = 'none';
        }
    }

    // 初始化所有 code-block
    function init() {
        document.querySelectorAll('.code-block').forEach(enhanceBlock);
    }

    // DOM ready
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', init);
    } else {
        init();
    }

    // 暴露给外部调用
    window.ZoeEditor = { init, enhanceBlock, runJS, copyCode };
})();
