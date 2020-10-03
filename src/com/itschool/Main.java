package com.itschool;

import java.io.IOException;

public class Main
{

	public static void main(String[] args) throws IOException
	{
		Database db = new Database();
		db.add("Egor", 18);
		db.add("John", 19);
		db.add("Bill", 59);
		db.add(new Student("Null", 2100000000));
		// System.out.println(db);

		long t1 = System.currentTimeMillis(), t2, t3, t4, t5, t6;
		for (int i = 0; i < 10; i++) {
			db.add("John" + Math.random(), (int) (Math.random() * 100));
		}
		t2 = System.currentTimeMillis() - t1;
		db.save("db.txt");
		db.clear();
		db.load("db.txt");

		//System.out.println(db);
		t3 = System.currentTimeMillis() - t2 - t1;
		// System.out.println("Current DB: " + db);
		//// System.out.println("Loaded DB: " + db);
		db.serialize("db_s.txt");
		db.clear();
		db.deserialize("db_s.txt");
		t4 = System.currentTimeMillis() - t3 - t2 - t1;

		//System.out.println("Loaded deseriaized DB: " + db);

		db.jacksonSerialize("students.json");
		db.clear();
		db.jacksonDeserialize("students.json");
		t5 = System.currentTimeMillis() - t4 - t3 - t2 - t1;

		// System.out.println(db);

		db.serializeFastJSON("db_fastjson.txt");
		db.clear();
		db.deserializeFastJSON("db_fastjson.txt");
//		System.out.println("Loaded fastJSON deseriaized DB: " + db);
		t6 = System.currentTimeMillis() - t5 - t4 - t3 - t2 - t1;

		System.out.println("Data Generation time:    " + t2);
		System.out.println("Text Save/load  time:    " + t3);
		System.out.println("Java serialization time: " + t4);
		System.out.println("Jackson ser/des time:    " + t5);
		System.out.println("FASTJson ser/des time:   " + t6);
/*
        System.out.println(db.remove(1));
        System.out.println(db);*/
	}
}
