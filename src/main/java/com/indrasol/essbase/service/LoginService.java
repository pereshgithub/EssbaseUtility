package com.indrasol.essbase.service;

import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import com.essbase.api.base.EssException;
import com.essbase.api.datasource.IEssCube;
import com.essbase.api.datasource.IEssOlapServer;
import com.essbase.api.domain.IEssDomain;
import com.essbase.api.metadata.IEssCubeOutline;
import com.essbase.api.metadata.IEssDimension;
import com.essbase.api.session.IEssbase;
import com.indrasol.essbase.pojo.Credentials;
import com.indrasol.essbase.utils.EssbaseUtil;

@Service
public class LoginService {

	private static final int FAILURE_CODE = 1;
	
	public void connectEssBase(Credentials credentials) {
		
		int statusCode = 0;
		IEssbase ess = null;
		IEssDomain dom 	= null;
		IEssOlapServer olapSvr = null;
		IEssCubeOutline otl = null;
		
try {
			
			ess = IEssbase.Home.create(IEssbase.JAPI_VERSION);
		
			dom 	= ess.signOn(credentials.getUsername(), credentials.getPassword(), false, null, credentials.getUrl());
			System.out.println("Domain Name: " + dom.getName().toString());
			olapSvr = (IEssOlapServer)dom.getOlapServer(credentials.getOlapServer());
			olapSvr.connect();
			System.out.println("olapSvr Name: " + olapSvr.getName().toString());
			IEssCube cube = olapSvr.getApplication("Sample").getCube("Basic");
			otl = cube.openOutline();
			
		
			List<IEssDimension> dimensionList = EssbaseUtil.getDimensionsAsList(otl);
			//credentials.setDimensionList(dimensionList);
			try {
				EssbaseUtil.createExcel(dimensionList);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			System.out.println("\nOutline Viewing sample complete.");
		} catch (EssException x) {
			System.err.println("Error:" + x.getMessage());
			statusCode = FAILURE_CODE;
		} 
		finally {
			try {
			if (olapSvr != null && olapSvr.isConnected() == true)
			olapSvr.disconnect();
			} catch (EssException x) {
			System.err.println("Error:" + x.getMessage());
			}

			try {
			if (ess != null && ess.isSignedOn() == true)
			ess.signOff();
			} catch (EssException x) {
			System.err.println("Error:" + x.getMessage());
			}

	}
	
	}

}
