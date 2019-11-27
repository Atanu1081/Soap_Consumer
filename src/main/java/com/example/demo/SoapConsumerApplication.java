package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pojoClasses.StudentDetailsRequest;
import pojoClasses.StudentDetailsResponse;

@SpringBootApplication
public class SoapConsumerApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(SoapConsumerApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(SOAPConnector soapConnector)
	{
		return args -> {

			String name = "Sukesh";
			if (args.length>0)
			{
				name=args[0];
			}

			StudentDetailsRequest request = new StudentDetailsRequest();
			request.setName(name);
			StudentDetailsResponse response = (StudentDetailsResponse)soapConnector.callWebService("http://localhost:8282/service/student-details",request);
			System.err.println("Got response as below");
			System.out.println("Name : " + response.getStudent().getName());
			System.out.println("Standard : " + response.getStudent().getStandard());
			System.out.println("Address : " + response.getStudent().getAddress());
		};

	}

}
