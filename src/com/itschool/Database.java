package com.itschool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Database
{
	public ArrayList<Student> list;

	public Database()
	{
		list = new ArrayList<>();
	}

	public void add(String name, int age)
	{
		this.list.add(new Student(name, age));
	}

	public Student get(int index)
	{
		return this.list.get(index);
	}

	public Student remove(int index)
	{
		return this.list.remove(index);
	}

	@Override
	public String toString()
	{
		return "Database{" + list + '}';
	}

	public void save(String filename) throws IOException
	{
		FileWriter outStream = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(outStream);
		for (Student student : list) {
			try {
				bw.write(student.name);
				bw.write(System.lineSeparator());
				bw.write(String.valueOf(student.age));
				bw.write(System.lineSeparator());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		bw.close();
		outStream.close();
	}

	public void load(String filename) throws IOException
	{
		this.clear();
		//this.list = new ArrayList<>();
		Scanner scanner = new Scanner(new FileReader(filename));
		String name = "";
		int age = -1;
		while (scanner.hasNextLine()) {
			name = scanner.nextLine();
			age = Integer.valueOf(scanner.nextLine());
			this.list.add(new Student(name, age));
		}
		scanner.close();
	}

	public void clear()
	{
		this.list.clear();
	}

	public void serialize(String filename)
	{
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.list);
			out.close();
			fileOut.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void deserialize(String filename)
	{
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.list = (ArrayList<Student>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
		catch (ClassNotFoundException c) {
			System.out.println("Student class not found");
			c.printStackTrace();
			return;
		}
	}

	public void jacksonSerialize(String filename) throws IOException
	{
		new ObjectMapper().writeValue(new File(filename), this);
	}

	public void jacksonDeserialize(String filename) throws IOException
	{
		Database db1 = new ObjectMapper().readValue(new File(filename), Database.class);
		this.list = db1.list;
	}

	public void serializeFastJSON(String filename) throws IOException
	{
		FileWriter outStream = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(outStream);
		bw.write(JSON.toJSONString(this.list));
		bw.close();
		outStream.close();
	}

	public void deserializeFastJSON(String filename) throws IOException
	{
		Scanner scanner = new Scanner(new FileReader(filename));
		this.clear();
		Object object = JSON.parseObject(scanner.nextLine(), ArrayList.class);
		for (JSONObject st : (ArrayList<JSONObject>) object) {
			this.add(new Student(st.getString("name"), st.getIntValue("age")));
		}
		//this.list = (ArrayList<Student>) JSON.parseObject(scanner.nextLine(), ArrayList.class);
		scanner.close();
	}

	public void add(Student student)
	{
		this.list.add(student);
	}
}
