import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class java_be_example {

	public static void main(String[] args) throws IOException {
	
		short val = 0x1234;
		byte[] b = ByteBuffer.allocate(2).putShort(val).array();
		
		System.out.println("Value write:");
		System.out.println(String.format("val = %x", val));
		System.out.println(String.format("b[0] = %x", b[0]));
		System.out.println(String.format("b[1] = %x", b[1]));
		System.out.println("");
		
		{ // write into output.txt
			System.out.println("Write " + String.format("val = %x", val) + " into output.txt"); 	
			FileOutputStream file = new FileOutputStream("../output.txt");
			file.write(b);
		    	file.close();
		}
	}
}
