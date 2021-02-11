# AppDevSec
# M1 Application Development Security : Secure Bank Examples

Our team :

BRUFAU Thomas
CHARBONNIER Pierric
GOOSSENS Benjamin
SOETENS Gatien

# How we ensure user is the right one starting the app ?

We verify the user's identity through the fire base authenticator tool. The user's has to give authentication informations which can be a email/password couple or a OAuth token given by a
federated identity provider. Those informations are then given to the Firebase SDK.
Of course this is not secure if the attacker have the password or has a mean to gain access to the password other than our application (for example brute force attack which according to https://howsecureismypassword.net/ would take 42 QUINTILLION YEARS).


# How do we securely save user's data on your phone ?

To securely store our data in the phone, we decided to use firebase firestore which allow us to create some collections which should be accessible directly by each user using their own UID. Then we could use this system to access our data without internet connection.


# How did we hide the API URL ?
To prevent attackers from gaining access to the source code we used the default installed obfuscator Proguard. It allow us to keep the source obfsucated even after being decompiled.


# Screenshots of your application
![plot](https://github.com/Gatens/AppDevSec/edit/api/homepage.png)
![plot](https://github.com/Gatens/AppDevSec/edit/api/display.png)
