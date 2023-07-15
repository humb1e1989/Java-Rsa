package com.company;
import java.math.BigInteger;
import java.security.SecureRandom;
/*
* 1. 首先明确所有的参数，n，e，d，p，q，phi(n)，c，m，其中质数p, q题目一般会给出，然后通过 n = pq 计算出n, 再通过 phi(n) = (p-1)(q-1) 计算出phi(n)
* 2. 生成两个大素数p和q（需要尽量不相近）
* 3. 计算n = pq
* 4. 计算phi(n) = (p-1)(q-1)
* 5，选择一个整数e，使得1 < e <= phi(n)，且e与phi(n)互质
* 6. 计算私钥d，使得ed = 1 mod phi，即d是e的模phi的乘法逆元
* 7. 加密：c = m^e mod N
* 8. 解密：m = c^d mod N
* */

public class Main {
    public static class RSA {
        private final static BigInteger ONE = new BigInteger("1");
        private final static SecureRandom random = new SecureRandom();
        private BigInteger privateKey;
        private BigInteger publicKey = BigInteger.valueOf(0);
        private BigInteger modulus;

        private int Digitals = 270; // set the digitals of the prime number p and q in RSA

        // Generate keys
        public RSA() {
            //Generate two distinct primes p and q.
            BigInteger p = generatePrime(Digitals); // prime p
            BigInteger q = generatePrime(Digitals); // Prime q
            System.out.println("p = " + p);
            System.out.println("q = " + q);

            // Generate modulus n
            modulus = p.multiply(q); // N = pq
            System.out.println("Modulus for this RSA is: " + modulus);

            // Generate phi(n)
            BigInteger phi_n = (p.subtract(ONE)).multiply(q.subtract(ONE)); // phi(n) = (p-1)(q-1)
            System.out.println("phi_n for this RSA is: "+ phi_n);

            //Generate public key e
            while((!publicKey.gcd(phi_n).equals(BigInteger.ONE))
                    || !(0 >= publicKey.compareTo(phi_n)) // less than or equal to phi_n
                    || !(0 <= publicKey.compareTo(BigInteger.ONE))) // greater than or equal to 1
            {
                publicKey = new BigInteger(phi_n.bitLength(),random).mod(phi_n); // e

            }
            System.out.println("Public key e = " + publicKey);

            //Generate private key d
            privateKey = publicKey.modInverse(phi_n); // d = e^-1 mod phi
            System.out.println("Private key d = " + privateKey);
        }

        // Generate an N-bit (roughly) prime
        private static BigInteger generatePrime(int digitals) {
            return BigInteger.probablePrime(digitals, random);
        }

        // Encrypt the given plaintext message
        public BigInteger encrypt(BigInteger plaintext) {
            return plaintext.modPow(publicKey, modulus);
        }

        // Decrypt the given ciphertext message
        public BigInteger decrypt(BigInteger ciphertext) {
            return ciphertext.modPow(privateKey, modulus);
        }

        public BigInteger signature(BigInteger planText) {
            return planText.modPow(privateKey, modulus);
        }

        public BigInteger verify(BigInteger cipherText) {
            return cipherText.modPow(publicKey, modulus);
        }

        // Return the private key as a string
        public BigInteger getPrivateKey() {
            return privateKey;
        }

        // Return the public key as a string
        public BigInteger getPublicKey() {
            return publicKey;
        }

        // Return the modulus as a string
        public BigInteger getModulus() {
            return modulus;
        }


        // 知道公钥e和模数n，求私钥d
        public BigInteger getPrivateKey(BigInteger e, BigInteger n) {
            return e.modInverse(n);
        }

        // 知道私钥d和模数n，求公钥e
        public BigInteger getPublicKey(BigInteger d, BigInteger n) {
            return d.modInverse(n);
        }
    }
    public static void main(String[] args) {
        RSA rsa = new RSA();

        BigInteger M = new BigInteger("9"); // Initialize the message M
        System.out.println("The PlainText is: " + M);

        BigInteger C = rsa.encrypt(M); // caculate the ciphertext C (Encrypted message)
        System.out.println("The CipherText is: " + C);


        BigInteger plaintext = rsa.decrypt(C); // Decrypted the ciphertext C
        System.out.println(plaintext);
        BigInteger ciphertext = rsa.encrypt(plaintext); // Encrypted the plaintext M
        System.out.println(ciphertext);


        if (M.equals(plaintext) && C.equals(ciphertext)){
            System.out.println("The result is correct!");
        }else{
            System.out.println("The result is wrong! Please double-check your code");
        }

//        System.out.println("Original message: " + message);
//        System.out.println("Encrypted message: " + ciphertext);
//        System.out.println("Decrypted message: " + plaintext);
//        System.out.println("Result: " + Result);
    }
}
