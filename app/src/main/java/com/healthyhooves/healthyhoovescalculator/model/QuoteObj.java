package com.healthyhooves.healthyhoovescalculator.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Graeme on 23/06/2016.
 */
public class QuoteObj implements Parcelable {

    private final long id;

    private final String date;

    private final int numCows;
    private final int numBaths;
    private final int bathCap;

    private final float numLtrs;
    private final float bathsBeforeChange;
    private final float ltrsHH;
    private final float kgCuSO4;

    private QuoteObj(@NonNull final Builder builder) {

        id = builder.id;
        date = builder.date;
        numCows = builder.numCows;
        numBaths = builder.numBaths;
        bathCap = builder.bathCap;
        numLtrs = builder.numLtrs;
        bathsBeforeChange = builder.bathsBeforeChange;
        ltrsHH = builder.ltrsHH;
        kgCuSO4 = builder.kgCuSO4;
    }

    private QuoteObj(@NonNull final Parcel parcel) {

        id = parcel.readLong();
        date = parcel.readString();
        numCows = parcel.readInt();
        numBaths = parcel.readInt();
        bathCap = parcel.readInt();
        numLtrs = parcel.readFloat();
        bathsBeforeChange = parcel.readFloat();
        ltrsHH = parcel.readFloat();
        kgCuSO4 = parcel.readFloat();
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getNumCows() {
        return numCows;
    }

    public int getNumBaths() {
        return numBaths;
    }

    public int getBathCap() {
        return bathCap;
    }

    public float getNumLtrs() {
        return numLtrs;
    }

    public float getBathsBeforeChange() {
        return bathsBeforeChange;
    }

    public float getLtrsHH() {
        return ltrsHH;
    }

    public float getKgCuSO4() {
        return kgCuSO4;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeLong(id);
        dest.writeString(date);
        dest.writeInt(numCows);
        dest.writeInt(numBaths);
        dest.writeInt(bathCap);
        dest.writeFloat(numLtrs);
        dest.writeFloat(bathsBeforeChange);
        dest.writeFloat(ltrsHH);
        dest.writeFloat(kgCuSO4);
    }

    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return false;
    }

    @Override public int hashCode() {
        // Temp, need to think about this.
        return Integer.toString(numCows).hashCode();
    }

    public static class Builder {

        private long id;

        private String date;

        private int numCows;
        private int numBaths;
        private int bathCap;

        private float numLtrs;
        private float bathsBeforeChange;
        private float ltrsHH;
        private float kgCuSO4;

        @NonNull
        public Builder setId(final long id) {
            this.id = id;
            return this;
        }

        @NonNull
        public Builder setDate(@Nullable final String date) {
            this.date = date;
            return this;
        }

        @NonNull
        public Builder setNumCows(final int numCows) {
            this.numCows = numCows;
            return this;
        }

        @NonNull
        public Builder setNumBaths(final int  numBaths) {
            this.numBaths = numBaths;
            return this;
        }

        @NonNull
        public Builder setBathCap(final int bathCap) {
            this.bathCap = bathCap;
            return this;
        }

        @NonNull
        public Builder setNumLtrs(final float numLtrs) {
            this.numLtrs = numLtrs;
            return this;
        }

        @NonNull
        public Builder setBathsBeforeChange(final float bathsBeforeChange) {
            this.bathsBeforeChange = bathsBeforeChange;
            return this;
        }

        @NonNull
        public Builder setLtrsHH(final float ltrsHH) {
            this.ltrsHH = ltrsHH;
            return this;
        }

        @NonNull
        public Builder setKgCuSO4(final float kgCuSO4) {
            this.kgCuSO4 = kgCuSO4;
            return this;
        }

        @NonNull
        public QuoteObj build() {
            return new QuoteObj(this);
        }
    }

    public static final Creator<QuoteObj> CREATOR = new Creator<QuoteObj>() {
        @Override
        public QuoteObj createFromParcel(final Parcel parcel) {
            return new QuoteObj(parcel);
        }

        @Override
        public QuoteObj[] newArray(final int size) {
            return new QuoteObj[size];
        }
    };
}
