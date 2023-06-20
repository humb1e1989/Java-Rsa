package com.company;

import java.math.BigInteger;
import java.security.SecureRandom;
public class Main {


    public static class RSA {
        private final static BigInteger ONE = new BigInteger("1");
        private final static SecureRandom random = new SecureRandom();

        private BigInteger privateKey;
        private BigInteger publicKey;
        private BigInteger modulus;

        // Generate an N-bit (roughly) prime
        private static BigInteger generatePrime(int N) {
            return BigInteger.probablePrime(N, random);
        }

        // Generate keys
        public RSA(int N) {
            BigInteger p = generatePrime(N); // prime p
            BigInteger q = generatePrime(N); // Prime q
            System.out.println(p);
            System.out.println(q);
            BigInteger phi_N = (p.subtract(ONE)).multiply(q.subtract(ONE)); // phi = (p-1)(q-1)
            modulus = p.multiply(q); // N = pq
            publicKey = new BigInteger("65537"); // common value in practice = 2^16+1, THIS IS e
            publicKey = BigInteger.valueOf(3);
            privateKey = publicKey.modInverse(phi_N); // d = e^-1 mod phi
        }

        // Encrypt the given plaintext message
        public BigInteger encrypt(BigInteger message) {
            return message.modPow(publicKey, modulus);
        }

        // Decrypt the given ciphertext message
        public BigInteger decrypt(BigInteger encrypted) {
            return encrypted.modPow(privateKey, modulus);
        }

        // Return the private key as a string
        public String getPrivateKey() {
            return privateKey.toString();
        }

        // Return the public key as a string
        public String getPublicKey() {
            return publicKey.toString();
        }

        // Return the modulus as a string
        public String getModulus() {
            return modulus.toString();
        }

        // 知道公钥e和模数n，求私钥d
        public BigInteger getPrivateKey(BigInteger e, BigInteger n) {
            return e.modInverse(n);
        }
        // 知道私钥d和模数n，求公钥e
        public BigInteger getPublicKey(BigInteger d, BigInteger n) {
            return d.modInverse(n);
        }
        //知道公钥e和私钥d，求模数n
        public BigInteger getModulus(BigInteger e, BigInteger d) {
            return e.multiply(d);
        }
        // 知道加密后的密文c和公钥e，求明文m
        public BigInteger getPlaintext(BigInteger c, BigInteger e) {
            return c.modPow(e, modulus);
        }
        // 知道明文m和私钥d，求密文c
        public BigInteger getCiphertext(BigInteger m, BigInteger d) {
            return m.modPow(d, modulus);
        }
    }
    public static void main(String[] args) {
        RSA rsa = new RSA(187);

        BigInteger message = new BigInteger("9");
        BigInteger ciphertext = rsa.encrypt(message);
        BigInteger plaintext = rsa.decrypt(ciphertext);
        BigInteger Result = rsa.getPrivateKey(BigInteger.valueOf(3),BigInteger.valueOf(187)); // 这里填方法的返回值

//        System.out.println("Original message: " + message);
//        System.out.println("Encrypted message: " + ciphertext);
//        System.out.println("Decrypted message: " + plaintext);
        System.out.println("Result: " + Result);
    }
}
