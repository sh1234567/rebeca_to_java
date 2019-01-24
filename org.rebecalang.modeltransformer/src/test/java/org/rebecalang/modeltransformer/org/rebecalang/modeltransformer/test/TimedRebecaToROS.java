package org.rebecalang.modeltransformer.org.rebecalang.modeltransformer.test;

import org.rebecalang.modeltransformer.Transform;


public class TimedRebecaToROS {
	String base = "src/test/resources/org/rebecalang/modeltransformer/testcase/Ticket.rebeca";
	
//	@Test 
	public void testDynamicModel() {
		String[] parameters = new String[] {
//				"--source", base + "/thermostat.rebeca",
//				"--source", base + "/prot-802-11.rebeca",
//				"--source", base + "/sensornetwork2.rebeca",
				"--source", base + "/TestFor.rebeca",
				"-e", "CoreRebeca",
				"-v", "2.1",
				"-o", "/tmp/user/java/bin/rosOut/",
//				"-o", "thermostat",
//				"-t", "RTMaude",
				"-t", "ROS",
		};
		
		try {
//			Transform.main(new String[0]);
			Transform.main(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TimedRebecaToROS testCase = new TimedRebecaToROS();
		testCase.testDynamicModel();
		System.out.println("done");
	}
}
