function actualizarBadgeCarrito() {
    fetch('/carrito')
        .then(r => r.text())
        .then(html => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, 'text/html');
            const items = doc.querySelectorAll('.cart-item-count');
            let total = 0;
            items.forEach(i => { total += parseInt(i.dataset.count) || 0; });
            const badge = document.getElementById('cart-badge');
            if (badge) {
                badge.textContent = total;
                badge.style.display = total > 0 ? 'inline' : 'none';
            }
        });
}

document.addEventListener('DOMContentLoaded', function () {

    actualizarBadgeCarrito();

    document.querySelectorAll('.add-to-cart-form').forEach(form => {
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const formData = new FormData(this);
            fetch(this.action, {
                method: 'POST',
                body: new URLSearchParams(formData)
            }).then(() => {
                actualizarBadgeCarrito();
                const toast = document.getElementById('cart-toast');
                if (toast) {
                    const bsToast = new bootstrap.Toast(toast);
                    bsToast.show();
                }
            });
        });
    });

    document.querySelectorAll('.qty-btn').forEach(btn => {
        btn.addEventListener('click', function () {
            const container = this.closest('.qty-container') || this.parentElement;
            const input = container.querySelector('.qty-input');
            if (!input) return;
            let value = parseInt(input.value) || 1;
            if (this.classList.contains('qty-plus')) {
                value = Math.min(value + 1, 99);
            } else {
                value = Math.max(value - 1, 1);
            }
            input.value = value;
            input.dispatchEvent(new Event('change'));
        });
    });

    document.querySelectorAll('.remove-from-cart').forEach(btn => {
        btn.addEventListener('click', function () {
            document.getElementById('removeForm' + this.dataset.productId).submit();
        });
    });

    const alert = document.querySelector('.alert-dismissible');
    if (alert) {
        setTimeout(() => {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 4000);
    }

});
