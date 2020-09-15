package com.keko.code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		/*
		 * we'll create a Stream object from a list of elements. Create the
         * PersonGenerator class to create a list of 10,000 Person objects, and use the
         * parallelStream() method of the List object to create the Stream . Then, use
         * the count() method of the Stream object to get the number of elements of the Stream
		 */
		System.out.println("From a Collection:");
		List<Person> persons=PersonGenerator.generatePersonList(10000);
		Stream<Person> personStream=persons.parallelStream();
		System.out.println("Number of persons: "+personStream.count());
		
		/*
		 * we'll create a Stream from a generator. Create an object of the MySupplier
		 * class. Then, use the static method generate() of the Stream class, passing
		 * the created object as a parameter to create the stream. Finally, use the
		 * parallel() method to convert the stream created to a parallel stream, the
		 * limit() method to get the first ten elements of the stream, and the forEach()
		 * method to print the elements of the stream:
		 */
		
		System.out.println("From a Supplier:");
		Supplier<String> supplier=new MySupplier();
		Stream<String> generatorStream=Stream.generate(supplier);
		generatorStream.parallel().limit(10).forEach( s-> System.out.println(s));
		
		/*
		 * we'll create a stream from a predefined list of elements. Use the static of()
		 * method of the Stream class to create the Stream . This method receives a
		 * variable list of parameters. In this case, we'll pass three String objects.
		 * Then, use the parallel() method of the stream to convert it to a parallel one
		 * and the forEach() method to print the values in the console
		 */
		
		System.out.println("From a predefined set of elements:");
		Stream<String> elementsStream=Stream.of("Peter", "John", "Mary");
		elementsStream.parallel().forEach( element -> System.out.println(element));
		
		/*
		 * we'll create a stream to read the lines of a file. First, create a
		 * BufferedReader object to read the file you want to read. Then, use the
		 * lines() method of the BufferedReader class to get a stream of String objects.
		 * Each element of this stream will be a line from the file. Finally, use the
		 * parallel() method to get a parallel version of the stream and the count()
		 * method to get the number of elements of the Stream . We also have to close
		 * the BufferedReader object:
		 */
		
		System.out.printf("From a File:\n");
		try (BufferedReader br = new BufferedReader(new
				FileReader("/var/log/auth.log"));) {
			Stream<String> fileLines = br.lines();
			System.out.printf("Number of lines in the file: %d\n\n",
			fileLines.parallel().count());
			System.out.printf("********************************************************\n");
			System.out.printf("\n");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Now, we'll create a Stream to process the contents of a folder. First, use the
		 * list() method of the Files class to get a stream of Path objects with the
		 * contents of the folder. Then, use the parallel() method of the Stream object to
         * convert it to a parallel stream and the count() method to count its elements.
         * Finally, in this case, we have to use the close() method to close the Stream:
		 */
		
		System.out.printf("From a Directory:\n");
		try {
			Stream<Path> directoryContent = Files.list(Paths.get(System.getProperty("user.home")));
			System.out.printf("Number of elements (files and folders):%d\n\n",
			directoryContent.parallel().count());
			directoryContent.close();
			System.out.printf("*********************************************************\n");
			System.out.printf("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * The next source we'll use is an Array . First, create an Array of strings. Then, use
		 * the stream() method of the Arrays class to create a stream from the elements of
		 * the array. Finally, use the parallel() method to convert the stream into a
		 * parallel one and the forEach() method to print the elements of the stream to the
		 * console:
		 */
		System.out.printf("From an Array:\n");
		String array[]={"1","2","3","4","5"};
		Stream<String> streamFromArray=Arrays.stream(array);
		streamFromArray.parallel().forEach(s->System.out.printf("%s : ", s));
		
		/*
		 * Now, we'll create a stream of random double numbers. First, create a Random
		 * object. Then, use the doubles() method to create a DoubleStream object. We'll
		 * pass the number 10 as a parameter to that method, so the stream we're going to
		 * create will have ten elements. Finally, use the parallel() method to convert the
		 * stream into a parallel one, the peek() method to write each element to the
		 * console, the average() method to calculate the average of the values of the
		 * stream, and the getAsDouble() method to get the value stored in the Optional
		 * object returned by the average() method		 *
		 */
		System.out.printf("\nGet the average of streams:\n");
		Random random = new Random();
		DoubleStream doubleStream = random.doubles(10);
		double doubleStreamAverage = doubleStream.parallel()
				.peek(d -> System.out.printf("%f :",d))
				.average().getAsDouble();
		
		/*
		 * Finally, we'll create a stream concatenating two streams. First, we create two
		 * streams of String objects using the of() method of the Stream class. Then, we
		 * use the concat() method of the Stream class to concatenate those streams into a
		 * unique one. Finally, we use the parallel() method of the Stream class to
		 * convert the stream into a parallel one and the forEach() method to write all the
		 * elements to the console:
		 */
		
		System.out.printf("\nConcatenating streams:\n");
		Stream<String> stream1 = Stream.of("1", "2", "3", "4");
		Stream<String> stream2 = Stream.of("5", "6", "7", "8");
		Stream<String> finalStream = Stream.concat(stream1, stream2);
		finalStream.parallel().forEach(s -> System.out.printf("%s : ", s));
		
	}

}
