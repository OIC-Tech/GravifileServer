package com.louishong.gravifile;

public class Testing {

    public Testing() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	try {
	    System.out.println(GravifileUserList.createXML(10, 1));
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
