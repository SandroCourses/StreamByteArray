import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collector;

public class Main {

	public static void main(String[] args) throws Exception {
		Main m = new Main();
		
		Path root = Paths.get("C:\\Users\\Sandro\\Documents\\stream_files");
		Path filePath = root.resolve(Paths.get("test.txt"));
		
		byte[] bytes = m.get(filePath);
		String s = new String(bytes);
		System.out.println(s);
	}
	
	public byte[] get(Path filePath) throws Exception {
		return Files.lines(filePath, StandardCharsets.ISO_8859_1)
			.collect(Collector.of(
				ByteArrayOutputStream::new,
				(c, s) -> {
					try {
						byte[] b = s.getBytes();
						c.write(b);
						c.write("\n".getBytes());
					} catch(Exception e) {
						e.printStackTrace();
					}
				},
				(result1, result2) -> result1,
				result -> result.toByteArray()));
	}
}