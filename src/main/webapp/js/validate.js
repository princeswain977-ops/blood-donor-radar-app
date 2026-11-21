// Client-side validation: runs in the browser before the form is sent to the server.
// The server checks the same rules again, so this is only for quick feedback.
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('form').forEach(function (form) {
        // Turn off the browser's own popups so we can show our messages instead
        form.setAttribute('novalidate', '');

        form.addEventListener('submit', function (event) {
            clearMessages(form);
            let valid = true;

            form.querySelectorAll('input, select, textarea').forEach(function (field) {
                if (!field.willValidate) {
                    return;
                }
                if (!field.checkValidity()) {
                    valid = false;
                    showMessage(field, field.validationMessage);
                }
            });

            if (!valid) {
                event.preventDefault();
            }
        });
    });
});

function showMessage(field, text) {
    const msg = document.createElement('span');
    msg.className = 'field-error js-error';
    msg.textContent = text;
    field.insertAdjacentElement('afterend', msg);
}

function clearMessages(form) {
    form.querySelectorAll('.js-error').forEach(function (el) {
        el.remove();
    });
}
