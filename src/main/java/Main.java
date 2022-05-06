import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import org.tinylog.Logger;


public class Main {

    public static void main(String[] args) {

        var q= 5009; var p= 269;
        var n=p*q; var phi= (p-1)*(q-1);
        var e = setE(phi);
        var d= setD(e, phi);
        var message= readInput();

        int[] publicKey ={e,n}; int[] privateKey ={d,n};

        var encrypted= encrypt(n, e, message);
        var decrypted = decrypt(encrypted, d, n);

        Logger.info("""
                    n értéke: {}
                    phi(n) értéke: {}
                    e értéke: {}
                    d értéke: {}
                    public key: ({}, {})
                    private key: ({}, {})
                    encrypted értéke: {}
                    decrypted értéke: {}""", n, phi, e, d, publicKey[0], publicKey[1], privateKey[0], privateKey[1], encrypted, decrypted);

    }


    static BigInteger decrypt(double encrypted, int d, int n){
        BigInteger encryptedMessage = BigDecimal.valueOf(encrypted).toBigInteger();
        BigInteger N = BigInteger.valueOf(n);
        return (encryptedMessage.pow(d)).mod(N);
    }


    static double encrypt(int n, int e, int message) {
        return Math.pow(message, e) % n;
    }


    static int setD(int e, int phi){
        for (int d = 0; d < phi; ++d){
            if (d * e % phi == 1){
                return d;
            }
        }
        return 0;
    }


    static int gcd(int a, int b)
    {
        int m = Math.min(a, b);
        for (int i = m; i > 1; i--) {
            if (a % i == 0 && b % i == 0)
                return i;
        }
        return 1;
    }


    static int setE(int phi){
        var e = 0;
        for (e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1) {
                break;
            }
        }
        return e;
    }


    static int readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
