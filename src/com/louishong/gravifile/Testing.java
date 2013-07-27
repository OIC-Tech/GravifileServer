package com.louishong.gravifile;

public class Testing {

    public Testing() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	try {
	    System.out.println(GravifileUserList.createXML());
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
