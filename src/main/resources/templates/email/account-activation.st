<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Activate Your Account</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap');
        
        body \{
            font-family: 'Roboto', Arial, sans-serif;
            line-height: 1.6;
            color: #333333;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        \}
        
        .container \{
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        \}
        
        .header \{
            text-align: center;
            padding: 20px 0;
        \}
        
        .logo \{
            max-width: 150px;
            height: auto;
        \}
        
        .content \{
            padding: 20px;
        \}
        
        .button-container \{
            text-align: center;
            margin: 30px 0;
        \}
        
        .button \{
            display: inline-block;
            padding: 12px 24px;
            background-color: #4285f4;
            color: #ffffff;
            text-decoration: none;
            border-radius: 4px;
            font-weight: 500;
            font-size: 16px;
        \}
        
        .footer \{
            text-align: center;
            padding: 20px;
            font-size: 12px;
            color: #757575;
            border-top: 1px solid #eeeeee;
        \}
        
        .alternative-link \{
            word-break: break-all;
            color: #4285f4;
        \}
        
        @media only screen and (max-width: 600px) \{
            .container \{
                width: 100%;
                padding: 10px;
            \}
            
            .content \{
                padding: 15px;
            \}
        \}
    </style>
</head>
<body>
    <div class="container">
        
        <div class="content">
            <h1>Welcome to Spaghetti AI!</h1>
            
            <p>Hello {name},</p>
            
            <p>Thank you for creating an account with us. We're excited to have you on board!</p>
            
            <p>To complete your registration and activate your account, please click the button below:</p>
            
            <div class="button-container">
                <a href="{activationUrl}" class="button">Activate My Account</a>
            </div>
            
            <p>If the button above doesn't work, you can also activate your account by copying and pasting the following link into your browser:</p>
            
            <p><a href="{activationUrl}" class="alternative-link">{activationUrl}</a></p>
            
            <p>Please note that this activation link will expire in 24 hours for security reasons.</p>
            
            <p>If you did not create an account with Spaghetti AI, please disregard this email.</p>
            
            <p>Thank you,<br>The Spaghetti AI Team</p>
        </div>
    </div>
</body>
</html>