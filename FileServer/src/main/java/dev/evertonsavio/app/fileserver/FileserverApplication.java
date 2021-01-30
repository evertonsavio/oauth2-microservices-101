package dev.evertonsavio.app.fileserver;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class FileserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileserverApplication.class, args);

		Devices d1 = new Devices(1L, "d1", "dev" );
		Devices d2 = new Devices(2L, "d2", "vev" );
		Devices d3 = new Devices(3L, "d3", "tev" );

		List<Devices> devices = new ArrayList<>();
		devices.add(d1);
		devices.add(d2);
		devices.add(d3);

		devices.forEach(System.out::println);

		Stream<String> stream = devices.stream().map(device -> device.getName());
		System.out.println(Arrays.toString(stream.toArray()));

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Stream<DevicesDto> listStream = devices.stream().map(dev -> modelMapper.map(dev, DevicesDto.class));
		List<DevicesDto> devDtos = listStream.collect(Collectors.toList());

		devDtos.forEach(System.out:: println);

	}//https://blog.netgloo.com/2014/12/18/handling-entities-inheritance-with-spring-data-jpa/

}
