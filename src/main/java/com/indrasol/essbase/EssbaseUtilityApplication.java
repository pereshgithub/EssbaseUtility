package com.indrasol.essbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.essbase.api.base.*;
import com.essbase.api.session.*;
import com.essbase.api.datasource.*;
//import com.essbase.api.dataquery.*;
import com.essbase.api.domain.*;
import com.essbase.api.metadata.*;

@SpringBootApplication
public class EssbaseUtilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EssbaseUtilityApplication.class, args);
	}
}
