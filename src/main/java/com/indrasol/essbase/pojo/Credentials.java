package com.indrasol.essbase.pojo;

import java.util.ArrayList;

import java.util.List;



import com.essbase.api.metadata.IEssDimension;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Credentials {

	private String url, username, password, olapServer;
	
	@Default
	private List<IEssDimension> dimensionList = new ArrayList<>();
}
