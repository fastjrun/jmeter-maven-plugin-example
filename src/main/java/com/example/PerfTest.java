package com.example;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class PerfTest extends AbstractJavaSamplerClient {
	private String a;
	private String b;
	private String resultData;

	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("num1", "");
		params.addArgument("num2", "");
		return params;
	}

	public SampleResult runTest(JavaSamplerContext arg0) {
		a = arg0.getParameter("num1");
		b = arg0.getParameter("num2");
		SampleResult sr = new SampleResult();
		sr.setSampleLabel("last");
		try {
			sr.sampleStart();
			Hello test = new Hello();
			resultData = String.valueOf(test.sum(Integer.parseInt(a),
					Integer.parseInt(b)));
			if (resultData != null && resultData.length() > 0) {
				sr.setResponseData("结果是：" + resultData, null);
				sr.setDataType(SampleResult.TEXT);
			}
			sr.setSuccessful(true);
		} catch (Throwable e) {
			sr.setSuccessful(false);
			e.printStackTrace();
		} finally {
			sr.sampleEnd();
		}
		return sr;
	}

}
