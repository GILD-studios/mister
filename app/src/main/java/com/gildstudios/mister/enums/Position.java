package com.gildstudios.mister.enums;


import com.gildstudios.mister.R;


public enum Position {

    D, M, F;

    public static Position fromResource(int resourceId) {
        switch (resourceId) {
            case R.drawable.d: return D;
            case R.drawable.c: return M;
            case R.drawable.a: return F;
        }

        return null;
    }

    public int toResource() {
        switch (this) {
            case D: return R.drawable.d;
            case M: return R.drawable.c;
            case F: return R.drawable.a;
        }

        return -100;
    }

    public Position next() {
        switch (this) {
            case D: return M;
            case M: return F;
            case F: return D;
        }

        return null;
    }

}
