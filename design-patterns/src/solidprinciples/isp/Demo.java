package solidprinciples.isp;

// ISP (Interface Segregation Principle)
public class Demo {

}

class Document {

}

interface Machine {
    void print(Document d);
    void fax(Document d) throws Exception;
    void scan(Document d);
}

class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void fax(Document d) {
        //
    }

    @Override
    public void scan(Document d) {
        //
    }
}


class OldFashionedPrinter implements Machine {

    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void fax(Document d) throws Exception {
        throw new Exception();
    }

    @Override
    public void scan(Document d) {

    }
}

interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

interface Fax {
    void fax(Document d);
}

class JustAPrinter implements Printer {

    @Override
    public void print(Document d) {

    }
}

// YAGNI = You ain't going to need it
class PhotoCopier implements Printer, Scanner {

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MultiFunctionDevice extends Printer, Scanner, Fax {}

class MultiFunctionMachine implements MultiFunctionDevice {
    private Printer printer;
    private Scanner scanner;
    private Fax fax;

    public MultiFunctionMachine(Printer printer, Scanner scanner, Fax fax) {
        this.printer = printer;
        this.scanner = scanner;
        this.fax = fax;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }

    @Override
    public void fax(Document d) {
        fax.fax(d);
    }
}