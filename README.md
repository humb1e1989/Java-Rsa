# Java-Rsa
## Introduction
> I complete this RSA formula in Java by using **Biginteger** and its self-contained functions.

## Workflow

#### Generate big prime p, q, n and phi(n)
> this code first generates a random set of prime number ***p*** and ***q*** by using the ***"Security Random()"*** function and ***"generateProbablePrime()"***, <br> Then calculate the modulus ***n = q * p*** and ***phi(n) = (p -1 )(q - 1)***.

#### Generate public key e and private key d
> Then select the public key ***e*** which is ***relative prime*** to the ***phi(n)***, by using the function Biginteger.gcd() = 1 <br>
> and e is in the interval ***(0, phi(n))***. <br>
> Here I randomly initiate a new Biginteger with maxium length equaling phi(n) as the value of the public key, and iterate the value of the public key by checking the condition:  <br> ***publickey.gcd(phi) == 1, and publickey.compareTo(phi(n)) == -1***

#### Encryption & Decryption & Signature & Verification
> Encryption: C = M^e mod n, using the modPow() function to calculate directly <br>
> Decryption: M = C^e mod n, using the modPow() function to calculate directly <br>
> Signature:  S = M^d mod n, using the modPow() function to calculate directly <br>
> Verification: M = S^e mod n, using the modPow() function to calculate directly <br>

#### Last Check
> In the main functioin, I do the double check that using the .equals() to check if the ***Message M*** equals what is decrpted and ***Cipher text C*** equals what is encrypted

## Demo
> ![Result](图片地址)
