package main;

import org.apache.commons.compress.compressors.CompressorException;

import java.io.IOException;

public class Runner {
	public static void main(String[] args) throws CompressorException {
		LoadFiles loadFiles = new LoadFiles();
		ComputeBaseStatistics computeBaseStatistics = new ComputeBaseStatistics();
		MaterializeSDTypes materializeSDTypes = new MaterializeSDTypes();
		MaterializeSDValidate materializeSDValidate = new MaterializeSDValidate();
		try {

			loadFiles.loadProperties("./viwiki-20180401-mappingbased-objects-uncleaned.ttl.bz2"); 									//./enwiki-20151002-mappingbased-objects-uncleaned.ttl
			loadFiles.createPropertyIndices();
			loadFiles.loadTypes("./viwiki-20180401-instance-types.ttl.bz2"); 										//./enwiki-20151002-instance-types-transitive.ttl
			loadFiles.createTypeIndices();
			loadFiles.loadDisambiguations("./viwiki-20180401-disambiguations.ttl.bz2");								//./enwiki-20151002-disambiguations-unredirected.ttl
			loadFiles.createDisambiguationIndices();
			computeBaseStatistics.computeGlobalTypeDistribution();
			computeBaseStatistics.computePerPredicateDistribution();
			materializeSDTypes.computeSDTypes();
			materializeSDTypes.writeTypeFile("./sdtypes.ttl", 0.4f);					//./sdtypes.ttl
			materializeSDValidate.computeSDValidateScores();
			materializeSDValidate.writeWrongStatementsFile("./sdinvalid.ttl", 0.15f);		//./sdinvalid.ttl
			if(1<0)
				throw new IOException();
		} catch (IOException e) {
			System.out.println("Error loading input files");
			e.printStackTrace();
		}
	}
}
