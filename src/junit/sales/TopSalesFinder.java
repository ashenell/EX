package junit.sales;

public class TopSalesFinder {

    private SalesRecord[] salesRecords = new SalesRecord[10];
    private int size = 0;


    private void ensureCapacity() {
        if (size == salesRecords.length) {
            SalesRecord[] newSalesRecords = new SalesRecord[salesRecords.length * 2];
            System.arraycopy(salesRecords, 0, newSalesRecords, 0, salesRecords.length);
            salesRecords = newSalesRecords;
        }
    }

    public void registerSale(SalesRecord record) {

        ensureCapacity();
        salesRecords[size++] = record;

    }

    public SalesRecordResult[] findItemsSoldOver(int amount) {

        // find ids of records that sold over specified amount.


        String[] ids = new String[size];
        int[] totals = new int[size];

        int uniqueCount = 0;



        return new SalesRecordResult[0];
    }

    public void removeSalesRecordsFor(String id) {

        int write = 0;

        for (int i = 0; i < size; i++) {
            if(!salesRecords[i].productId().equals(id)) {
                salesRecords[write++] = salesRecords[i];
            }
        }

        for (int i = write; i < size; i++) {
            salesRecords[i] = null;
        }
    }

    public SalesRecord[] getAllRecordsPaged(int pageNumber, int pageSize) {

        // return a slice of all stored records

        return new SalesRecord[0];
    }

    public int getRecordCount() {
        // only needed for the sample application

        // returns the count of all records

        return 0;
    }

    public void removeRecord(String id) {
        // only needed for the sample application

        // removes record with specific id
    }

}


