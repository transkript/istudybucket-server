<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verification Email</title>
    <link rel="stylesheet" href="/static/css/verify-email.css">
</head>
<body>
    <header>
        This is a mail template
    </header>
    <section>
        <div>
            <span>Hello, ${recipientName}</span>
        </div>
        <div>
            Thanks for creating an account on our platform. To activate your account, click on the button below:
            <br>
            <button><a href="${verificationUrl}">${message}</a></button>
        </div>
    </section>
    <footer></footer>
</body>
</html>