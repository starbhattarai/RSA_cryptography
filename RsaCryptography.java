import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Rsacryptography
{
	public long[] stringToNumber( String intext)      //Convert String characters to the corresponding ascii numbers
	{
		int length=intext.length();
		long[] stringNumber=new long[length];
		char character;
		
		for(int i=0; i<length; i++)
		{
			character=intext.charAt(i);
			
			stringNumber[i]=(int) character;
		}
	return stringNumber;
	}
	
	public String numberToString(long[] number)        //Convert numbers to character
	{

		String i="";
for(long l:number){
	i += (char) l;
}

return i;
	}

	public long modulusPower(long a, long b, long c)      //Calculate (a^b)%c

        {

            long result = 1;

            for (int i = 0; i < b; i++)

            {

                result *= a;

                result %= c; 

            }

            return result % c;

        }


        public long modulusOfMultiply(long a, long b, long c)         //Calculate (a * b) % c 

        {

            return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(c)).longValue();

        }

	

	public boolean isPrime(long n, int iteration)

        {

            if (n == 0 || n == 1)                        //Base case for Primality test

                return false;

            if (n == 2)

                return true;

            if (n % 2 == 0)                              // Even number except 2 are composite
                return false;
	    long s = n - 1;
	    while (s % 2 == 0)

                s /= 2;

     

            Random random = new Random();

            for (int i = 0; i < iteration; i++)
	        {
	        long r = Math.abs(random.nextLong());          

                long a = r % (n - 1) + 1, temp = s;

                long mod = modulusPower(a, temp, n);

                while (temp != n - 1 && mod != 1 && mod != n - 1)

                {

                    mod = modulusOfMultiply(mod, mod, n);

                    temp *= 2;

                }

                if (mod != n - 1 && temp % 2 == 0)

                    return false;
	        }

            return true;        

        }

	public static void main(String[] args)
	{
		int number1,number2,number3, repetation;
		boolean result;
		Scanner input=new Scanner(System.in);
		System.out.println("Enter a first number to test the primality");				//Primality testing of number
		number1=input.nextInt();
		System.out.println("Enter a second number to test the primality");				
		number2=input.nextInt();
		System.out.println("Enter a third number to test the primality");				
		number3=input.nextInt();		
		System.out.println("Enter the repetaion to be used for primality test");
		repetation=input.nextInt();
		Rsacryptography ob1 = new Rsacryptography();
		result=ob1.isPrime(number1,repetation);
		if(result)
			System.out.println("Given number  "+number1+"  is Prime");
		else
			System.out.println("Given number  "+number1+"  is not Prime");

		result=ob1.isPrime(number2,repetation);
		if(result)
			System.out.println("Given number  "+number2+"  is Prime");
		else
			System.out.println("Given number  "+number2+"  is not Prime");
		result=ob1.isPrime(number3,repetation);
		if(result)
			System.out.println("Given number  "+number3+"  is Prime");
		else
			System.out.println("Given number  "+number3+"  is not Prime");

		long prime1,prime2,numberp1p2,phyofn,exponent,decryption;					//RSA Implementaion
		System.out.println("Please enter the first Prime number for RSA algorithm");
		prime1=input.nextLong();
		System.out.println("Please enter the second Prime number for RSA algorithm");
		prime2=input.nextLong();
		numberp1p2=prime1*prime2;
		phyofn=(prime1-1)*(prime2-1);
		System.out.println("Please enter the exponent for RSA algorithm");
		exponent=input.nextLong();
		
		//decryption=((2*phyofn)+1)/exponent;
		BigInteger b1,b2,b3;
		b1=BigInteger.valueOf(exponent);
		b2=BigInteger.valueOf(phyofn);
		b3=b1.modInverse(b2);  											//e.modInv(phin)
		decryption=b3.longValue();
		System.out.println("Receiver's two public keys are: "+"("+numberp1p2+","+exponent+")");
		

		Scanner input1=new Scanner(System.in);									//Input text manipulation and encryption
		System.out.println("Please enter the text to send to the receiver");
		String text=input1.nextLine();
		
		long[] plaintextInNumber=new long[text.length()];
		System.out.println("Plaintext to send to the receiver in character string is:  "+text);
		plaintextInNumber=ob1.stringToNumber(text);
		System.out.print("\nPlaintext to send to the receiver in numbers is:  ");
		for (long n:plaintextInNumber)
			System.out.print(n+"  ");	
			
		long[] numberafterencrypt = new long[text.length()];


		BigInteger[] enc = new BigInteger[text.length()];


		System.out.print("\n\nResulting ciphertext in number is: ");			      			//Encryption
		for(int i=0;i<text.length();i++) 
		{
			enc[i] =  (new BigInteger(plaintextInNumber[i]+"")).modPow(new BigInteger(exponent+""),new BigInteger(numberp1p2+"")); 
			System.out.print(enc[i]+" ");
		}	
		System.out.println();
		long[] numberafterdecrypt=new long[text.length()];		
		System.out.print("\nNumber after decryption is: ");
		for(int i=0; i<text.length();i++)									//Decryption
		{
			
			enc[i] = enc[i].modPow(new BigInteger(decryption+""),new BigInteger(numberp1p2+""));

			System.out.print(enc[i]+" ");
			
		}
		System.out.println();
		System.out.print("\nPlaintext received by the receiver is: ");                       		 //+ob1.numberToString(numberafterdecrypt));
		for(BigInteger bg: enc){
			System.out.print((char) Integer.parseInt(bg+""));
		}
		System.out.println();

	}
 
}
