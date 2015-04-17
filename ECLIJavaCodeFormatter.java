import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.jface.text.BadLocationException;


public class ECLIJavaCodeFormatter
{
    public static void main(String[] args)
    {
        BufferedInputStream configStream = null;
        try {
            configStream = new BufferedInputStream(new FileInputStream(args[0]));
        } catch (FileNotFoundException e) {
            System.err.printf("Config file %s not found.\n", args[0]);
            System.exit(1);
        }
        Properties config = new Properties();
        try { config.load(configStream); }
        catch (IOException ioe) {
            System.err.println("Error while loading config");
            System.exit(1);
        }
        finally {
            try { configStream.close(); }
            catch (IOException e) {}
        }
        CodeFormatter formatter = ToolFactory.createCodeFormatter(config);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(args[1]),
                                       Charset.forName("ISO-8859-1"));
        } catch (IOException e) {
            System.err.printf("Cannot open file %s\n", args[1]);
            System.exit(1);
        }
        String source = "";
        for (int i = 0; i < lines.size(); i++)
            source += lines.get(i);
        IDocument doc = new Document();
        doc.set(source);
        TextEdit edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT |
                                         CodeFormatter.F_INCLUDE_COMMENTS,
                                         source, 0, source.length(), 0, null);
        if (edit == null)
        {
            System.err.println("Format error occured");
            System.exit(1);
        }
        try {
            edit.apply(doc);
            System.out.println(doc.get());
        }
        catch (MalformedTreeException e) {
            System.err.println("MalformedTree");
        }
        catch (BadLocationException e) {
            System.err.println("BadLocation");
        }
    }
}
