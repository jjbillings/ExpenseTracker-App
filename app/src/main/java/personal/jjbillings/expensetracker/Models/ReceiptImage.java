package personal.jjbillings.expensetracker.Models;

import java.util.Date;

/**
 * TODO: Determine how we are going to deal with the images. Right now I don't want to read them all into memory at once.
 * TODO: Which is what I think will happen if I, say, throw a bitmap into this object.
 * Created by jbillz on 9/8/16.
 */
public class ReceiptImage {

    private int id;
    private String path, filename;
    private Date creationDate;

    private ReceiptImage(Builder b) {
        this.id = b.id;
        this.path = b.path;
        this.filename = b.filename;
        this.creationDate = b.creationDate;
    }

    private static class Builder {
        private final int id;
        private final String path, filename;
        private final Date creationDate;

        public Builder(int id, String path, String filename, Date creationDate) {
            this.id = id;
            this.path = path;
            this.filename = filename;
            this.creationDate = creationDate;
        }

        public ReceiptImage build() {
            return new ReceiptImage(this);
        }
    }


}
