import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.sun.javafx.scene.layout.region.Margins
import org.scilab.forge.jlatexmath.TeXConstants
import org.scilab.forge.jlatexmath.TeXFormula
import java.awt.Color
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import com.itextpdf.text.PageSize
import java.io.File
import java.io.FileOutputStream
import com.itextpdf.text.pdf.PdfWriter
import java.util.*


object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        //"x=\\frac{-b \\pm \\sqrt {b^2-4ac}}{2a}"
        val form = TeXFormula(createTeXString(50))
        print(System.getProperty("user.dir"))
        form.createPNG(TeXConstants.STYLE_DISPLAY, 20f, System.getProperty("user.dir")+"/5MinuteTask.png", Color.WHITE, Color.BLACK)
        val outputFile = "5MinAufgaben.pdf"
        val latexImage = "5MinuteTask.png"

        val document = Document()
        PdfWriter.getInstance(document, FileOutputStream(File(System.getProperty("user.dir")+"/", outputFile)))
        document.open()
        document.pageSize = PageSize.A4
        document.newPage()
        val image = Image.getInstance(File(System.getProperty("user.dir")+"/", latexImage).absolutePath)
        val margin = 20f
        image.setAbsolutePosition(margin, PageSize.A4.height - image.height - margin )
        image.borderWidth = 0f
        //image.scaleAbsolute(PageSize.A4)
        document.add(image)
        document.close()
        File(System.getProperty("user.dir")+"/", latexImage).delete()
    }

    fun createTeXString(numberOfTasks: Int): String {
        val rand = Random()
        val newline = "\\\\"
        var output = "\\text{1. Löse folgende Aufgaben:} $newline \\text{} $newline"

        for (i in 1..numberOfTasks) {
            val firstNumber = rand.nextInt(100)
            val secondNumber = rand.nextInt(100)
            output += "~~~~$firstNumber + $secondNumber = $newline"
        }
        return output
    }

}