// ============================================================
// 导航栏功能
// ============================================================

document.addEventListener('DOMContentLoaded', function() {
    // 当前页面高亮
    const currentPath = window.location.pathname;
    const navLinks = document.querySelectorAll('.nav-menu a');
    
    navLinks.forEach(link => {
        if (currentPath.includes(link.getAttribute('href'))) {
            link.classList.add('active');
        }
    });

    // 代码复制功能
    document.querySelectorAll('.code-copy').forEach(btn => {
        btn.addEventListener('click', function() {
            const codeBlock = this.closest('.code-block');
            const code = codeBlock.querySelector('pre').textContent;
            
            navigator.clipboard.writeText(code).then(() => {
                this.textContent = '已复制!';
                setTimeout(() => {
                    this.textContent = '复制';
                }, 2000);
            });
        });
    });

    // 侧边栏滚动高亮
    const sections = document.querySelectorAll('.content h2[id]');
    const sidebarLinks = document.querySelectorAll('.sidebar-menu a');

    window.addEventListener('scroll', () => {
        let current = '';
        sections.forEach(section => {
            const sectionTop = section.offsetTop;
            if (scrollY >= sectionTop - 100) {
                current = section.getAttribute('id');
            }
        });

        sidebarLinks.forEach(link => {
            link.classList.remove('active');
            if (link.getAttribute('href') === '#' + current) {
                link.classList.add('active');
            }
        });
    });
});
