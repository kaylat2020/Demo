package com.example.vaulttechdemo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

public class Transaction implements Parcelable {
    /**
     * This field is needed for Android to be able to
     * create new objects, individually or as arrays
     *
     * If you don’t do that, Android framework will raises an exception
     * Parcelable protocol requires a Parcelable.Creator object called CREATOR
     */
    public static final Parcelable.Creator<Transaction> CREATOR
            = new Parcelable.Creator<Transaction>()
    {
        public Transaction createFromParcel(Parcel in)
        {
            return new Transaction(in);
        }

        public Transaction[] newArray(int size)
        {
            return new Transaction[size];
        }
    };

    //[amt,date,name,Card]
    private final String amt, date, name, card;
    private String[] block;
    private final DecimalFormat df = new DecimalFormat("#.##"); // for amt

    //Constructor
    public Transaction( String amt, String date, String name, String card )
    {
        block = new String[]{amt,date,name,card};
        this.amt = block[0];
        this.date = block[1];
        this.name = block[2];
        this.card = block[3];
    }

    //USE THESE
    public Transaction(Parcel in)
    {
        String[] data = new String[4];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.amt = data[0];
        this.name = data[1];
        this.date = data[2];
        this.card = data[3];
    }

    public String[] getTransaction() {
        return block;
    }
    public double getAmount()
    {
        //TODO this is causing problems on the current results screen
        return Double.parseDouble(amt);
    }

    /**
     * Define the kind of object that you gonna parcel,
     * You can use hashCode() here
     */
    @Override
    public int describeContents()
    {
        return 0;
    }
    /**
     * Actual object serialization happens here, Write object content
     * to parcel, reading should be done according to this write order
     * @param dest - parcel
     * @param flags - Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeStringArray( getTransaction() );
    }
    @NonNull
    @Override
    public String toString()
    {
        return "Transaction{" +
                this.amt + ", '" + this.name + '\'' + ", " + this.date + ", " + this.card + '}';
    }
}
