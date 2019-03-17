package com.cegielskir.formwork.builder.computing;

public class GirderValue implements Comparable {

    public final int n;
    public final float len;
    public final float max;
    public final boolean isMain;

    public GirderValue(int n, float len, float max, boolean isMain) {
        this.n = n;
        this.len = len;
        this.max = max;
        this.isMain = isMain;
    }

    @Override
    public int compareTo(Object o) {
        GirderValue girVal = (GirderValue) o;
        float x1, x2;
        x1 = this.len / (this.n - 1);
        if(this.isMain){
            if(this.max == 3.3f) x1 = x1 - 0.08f;
            if(this.max == 3.0f) x1 = x1 - 0.04f;
        }
        x2 = girVal.len / (girVal.n - 1);
        if(girVal.isMain){
            if(girVal.max == 3.3f) x2 = x2 - 0.08f;
            if(girVal.max == 3.0f) x2 = x2 - 0.04f;
        }
        if(x1 < 0.3f && x2 < 0.3f){
            if(this.n > girVal.n) return 1;
            else if(this.n < girVal.n) return -1;
            else {
                if(this.len > girVal.len) return 1;
                else if(this.len < girVal.len) return -1;
                else return 0;
            }
        }
        if(this.len > girVal.len) return 1;
        else if(this.len < girVal.len) return -1;
        else {
            if(this.n > girVal.n) return 1;
            else if(this.n < girVal.n) return -1;
            else return 0;
        }

    }

    @Override
    public String toString() {
        return "GirderValue{" +
                "n=" + n +
                ", len=" + len +
                '}';
    }
}
