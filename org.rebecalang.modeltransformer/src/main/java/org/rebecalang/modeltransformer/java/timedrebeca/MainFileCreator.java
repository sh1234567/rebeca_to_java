package org.rebecalang.modeltransformer.java.timedrebeca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.apache.maven.artifact.repository.metadata.Metadata;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.FieldDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MainRebecDefinition;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MsgsrvDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.OrdinaryPrimitiveType;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.VariableDeclarator;
import org.rebecalang.compiler.utils.CodeCompilationException;
import org.rebecalang.compiler.utils.CompilerFeature;
import org.rebecalang.compiler.utils.TypesUtilities;
import org.rebecalang.modeltransformer.TransformingFeature;

public class MainFileCreator {
	public final static String NEW_LINE = "\r\n";
	public final static String TAB = "\t";
	private String modelName;
	private RebecaModel rebecaModel;
	private CoreRebecaStatementTransformer statementTransformer;
	private CoreRebecaExpressionTransformer expressionTransformer;

	public MainFileCreator(RebecaModel rebecaModel, String modelName, Set<CompilerFeature> compilerFeatures,
			Set<TransformingFeature> transformingFeatures) {
		// TODO Auto-generated constructor stub
		this.modelName = modelName;
		this.rebecaModel = rebecaModel;
	}

	public String getMainFileContent() {
		// TODO Auto-generated method stub
		String retValue = "package " + modelName + ";\r\n" + "import java.io.IOException;\r\n" + 
				"import java.util.*;\r\n" + 
				"import com.rits.cloning.Cloner;\r\n";
		retValue += "public class Main {\r\n" + "public static void main(String[] args) throws CloneNotSupportedException {\r\n"
				+ "String mode = \"i\";\r\n"
				+ "String programRetValue = \"\";\r\n"
				+ "Queue<State> queue = new LinkedList<State>();\r\n"
				+ "Queue<State> queue_2 = new LinkedList<State>();\r\n"
				+ "MessageQueue<Message> mq = new MessageQueue<Message>();\r\n"
				+ "Cloner cloner = new Cloner();\r\n"
				+ "float t_1 = 0;\r\n"
				+ "float t_2 = 0;\r\n"
				+ "int id = 0;\r\n"
				+ "int states_num = 0;\r\n"
				+ "Message a = new Message();\r\n" + "Actors[] actors = new Actors[10];\r\n" + "String[] actorsNames = new String[10];\r\n" +
						"int actorId = 0;\r\n" + defineRebecs() + "State s_0 = new State();\r\n" + 
								"s_0.setActors(actors);\r\n" + 
								"s_0.setMessageQueue(mq);\r\n" + 
								"queue.add(s_0);\r\n" + "states_num += 1;\r\n" +
								"System.out.println(printState(s_0, states_num));\r\n" +
								"programRetValue += printState(s_0, states_num);\r\n" + "int n = 0;\r\n"
				+ discreteTimeQueueManagement() + continuousTimeQueueManagement() + "\r\n" + "DirectoryCreator directoryCreator = new DirectoryCreator();\r\n" + 
						"try {\r\n" + 
						"directoryCreator.addFile(\"prog_notes.txt\", programRetValue);\r\n" + 
						"} catch (IOException e) {\r\n" + 
						"// TODO Auto-generated catch block\r\n" + 
						"e.printStackTrace();\r\n" + 
						"}\r\n" + "}\r\n" + containsFunction() + printMessageFunction() + printStateFunction() + printStatesQueueFunction() + "}";
		return retValue;
	}

	private String continuousTimeQueueManagement() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "else if (mode.equals(\"i\")) {\r\n" + 
				"while (!queue.isEmpty()) {\r\n" + 
				"State s_1 = new State();\r\n" + 
				"State s_2 = new State();\r\n" + 
				"s_1 = queue.poll();\r\n" + 
				"queue_2.add(s_1);\r\n" + 
				"State s = cloner.deepClone(s_1);\r\n" + 
				"// ArrayList<Message> messages = new ArrayList<Message>();\r\n" + 
				"Message m = new Message();\r\n" + 
				"ArrayList<TimePoint> timePoints = new ArrayList<TimePoint>();\r\n" + 
				"ArrayList<TimePoint> timePoints_2 = new ArrayList<TimePoint>();\r\n" + 
				"while (!s.getMessageQueue().isEmpty()) {\r\n" + 
				"TimePoint tp_1 = new TimePoint();\r\n" + 
				"TimePoint tp_2 = new TimePoint();\r\n" + 
				"m = s.getMessageQueue().remove();\r\n" + 
				"tp_1.setTime(m.getAfter_1());\r\n" + 
				"tp_1.setType(\"b\");\r\n" + 
				"timePoints.add(tp_1);\r\n" + 
				"tp_2.setTime(m.getAfter_2());\r\n" + 
				"tp_2.setType(\"e\");\r\n" + 
				"timePoints.add(tp_2);\r\n" + 
				"}\r\n" + 
				"timePoints_2 = cloner.deepClone(timePoints);\r\n" + 
				"if (!timePoints_2.isEmpty()) {\r\n" + 
				"float min_1 = timePoints_2.remove(0).getTime();\r\n" + 
				"float min_2 = timePoints_2.remove(0).getTime();\r\n" + 
				"while (min_2 == min_1 && !timePoints_2.isEmpty()) {\r\n" + 
				"min_2 = timePoints_2.remove(0).getTime();\r\n" + 
				"}\r\n" + 
				"float r = 0;\r\n" + 
				"if (min_2 < min_1) {\r\n" + 
				"r = min_1;\r\n" + 
				"min_1 = min_2;\r\n" + 
				"min_2 = r;\r\n" + 
				"}\r\n" + 
				"int l = timePoints_2.toArray().length;\r\n" + 
				"for (int i = 0; i < l; i++) {\r\n" + 
				"float new_t = timePoints_2.remove(0).getTime();\r\n" + 
				"if (new_t < min_1) {\r\n" + 
				"min_2 = min_1;\r\n" + 
				"min_1 = new_t;\r\n" + 
				"} else if (new_t < min_2 && new_t != min_1) {\r\n" + 
				"min_2 = new_t;\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"s = cloner.deepClone(s_1);\r\n" + 
				"while (!s.getMessageQueue().isEmpty()) {\r\n" + 
				"Message msg = s.getMessageQueue().remove();\r\n" + 
				"if (msg.getAfter_1() == min_1 && msg.getAfter_2() == min_1) {\r\n" + 
				"min_2 = min_1;\r\n" + 
				"break;\r\n" + 
				"}\r\n" + 
				"}" +
				"System.out.println(\"min_1: \" + min_1);\r\n" + 
				"programRetValue += \"min_1: \" + min_1 + \"\\r\\n\";\r\n" +
				"System.out.println(\"min_2: \" + min_2);\r\n" + 
				"programRetValue += \"min_2: \" + min_2  + \"\\r\\n\";\r\n" +
				"t_1 = min_1;\r\n" +
				"t_2 = min_2;\r\n" +
				"s = cloner.deepClone(s_1);\r\n" + 
				"ArrayList<Message> m_1 = new ArrayList<Message>();\r\n" + 
				"ArrayList<Message> m_2 = new ArrayList<Message>();\r\n" + 
				"while (!s.getMessageQueue().isEmpty()) {\r\n" + 
				"Message msg = s.getMessageQueue().remove();\r\n" + 
				"if (msg.getAfter_1() == min_1) {\r\n" + 
				"m_1.add(cloner.deepClone(msg));\r\n" + 
				"}\r\n" + 
				"if (msg.getAfter_2() == min_2) {\r\n" + 
				"m_2.add(cloner.deepClone(msg));\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"s = cloner.deepClone(s_1);\r\n" + 
				"State s_prime = cloner.deepClone(s_1);\r\n" +
				"if (!m_1.isEmpty()) {\r\n" + 
				"for (int i = 0; i < m_1.toArray().length; i++) {\r\n" + 
				"s = cloner.deepClone(s_1);\r\n" + 
				"State new_s = cloner.deepClone(s_1);\r\n" + 
				"Message new_m = new Message();\r\n" + 
				"MessageQueue<Message> mq_3 = new MessageQueue<Message>();\r\n" + 
				"while (!s.getMessageQueue().isEmpty()) {\r\n" + 
				"new_m = s.getMessageQueue().remove();\r\n" + 
				"if (!(new_m.equals(m_1.get(i)))) {\r\n" + 
				"mq_3.add(new_m);\r\n" + 
				"} else {\r\n" + 
				"a = new_m;\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"System.out.println(printMessage(a));\r\n" + 
				"programRetValue += printMessage(a) + \"\\r\\n\";\r\n" +
				"new_s.setMessageQueue(mq_3);\r\n" + 
				"new_s.setState_time_1(min_1);\r\n" + 
				"new_s.setState_time_2(min_2);\r\n" +
				"System.out.println(printState(new_s, 0));\r\n" +
				"programRetValue += printState(new_s, 0);\r\n"; 
		for (ReactiveClassDeclaration rc : rebecaModel.getRebecaCode().getReactiveClassDeclaration()) {
			for (MsgsrvDeclaration msgsrv : rc.getMsgsrvs()) {
				for (MainRebecDefinition md : rebecaModel.getRebecaCode().getMainDeclaration()
						.getMainRebecDefinition()) {
					ReactiveClassDeclaration metaData;
					try {
						metaData = TypesUtilities.getInstance().getMetaData(md.getType());
						if (rc.getName() == metaData.getName()) {
							retValue += "id = 0;\r\n" + 
									"for (int j = 0; j < actorsNames.length; j++) {\r\n" + 
									"if (actorsNames[j] != null && actorsNames[j].equals(\"" + md.getName() + "\")) {\r\n" + 
									"id = j;\r\n" + 
									"break;\r\n" + 
									"}\r\n" + 
									"}\r\n";
							retValue += "if (a.getReceiver().equals(\"" + md.getName()
									+ "\") && a.getMsgName().equals(\"" + msgsrv.getName() + "\")" + " && actors[id].getClass().getSimpleName().equals(\"" + metaData.getName() +"\")" + ") {\r\n"
									+ "s_2 = " + "(("+ metaData.getName() +") new_s.getActors()[id])" + "." + msgsrv.getName() + "(t_1, t_2, new_s, mode);\r\n" + "if (!contains(queue, s_2, mode) && !contains(queue_2, s_2, mode)) {\r\n" + 
											"queue.add(s_2);\r\n" + "states_num += 1;\r\n" + "System.out.println(printState(s_2, states_num));\r\n" + "programRetValue += printState(s_2, states_num);\r\n" +
											"}" + "else {\r\nSystem.out.println(\"equal:\\r\\n\" + printState(s_2, 0));\r\n" + "programRetValue += \"equal:\\r\\n\" + printState(s_2, 0);\r\n" + "}\r\n}\r\n";

						}

					} catch (CodeCompilationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
				retValue += "\r\n" + "}\r\n" + "}\r\n" + "if (m_2.isEmpty()) {\r\n" + 
						"\r\n" + 
						"System.out.println(\"time passing\");\r\n" + 
						"programRetValue += \"time passing\" + \"\\r\\n\";\r\n" +
						"s = cloner.deepClone(s_prime);\r\n" + 
						"State new_s = cloner.deepClone(s_1);\r\n" + 
						"MessageQueue<Message> mq_3 = new MessageQueue<Message>();\r\n" + 
						"while (!s.getMessageQueue().isEmpty()) {\r\n" + 
						"Message msg = s.getMessageQueue().remove();\r\n" + 
						"if (msg.getAfter_1() < min_2) {\r\n" + 
						"msg.setAfter_1(min_2);\r\n" + 
						"}\r\n" + 
						"mq_3.add(msg);\r\n" + 
						"}\r\n" + 
						"new_s.setMessageQueue(mq_3);\r\n" + 
						"s = cloner.deepClone(new_s);\r\n" + 
						"m = new Message();\r\n" + 
						"timePoints = new ArrayList<TimePoint>();\r\n" + 
						"timePoints_2 = new ArrayList<TimePoint>();\r\n" + 
						"while (!s.getMessageQueue().isEmpty()) {\r\n" + 
						"TimePoint tp_1 = new TimePoint();\r\n" + 
						"TimePoint tp_2 = new TimePoint();\r\n" + 
						"m = s.getMessageQueue().remove();\r\n" + 
						"tp_1.setTime(m.getAfter_1());\r\n" + 
						"tp_1.setType(\"b\");\r\n" + 
						"timePoints.add(tp_1);\r\n" + 
						"tp_2.setTime(m.getAfter_2());\r\n" + 
						"tp_2.setType(\"e\");\r\n" + 
						"timePoints.add(tp_2);\r\n" + 
						"}\r\n" + 
						"timePoints_2 = cloner.deepClone(timePoints);\r\n" + 
						"if (!timePoints_2.isEmpty()) {\r\n" + 
						"min_1 = timePoints_2.remove(0).getTime();\r\n" + 
						"min_2 = timePoints_2.remove(0).getTime();\r\n" + 
						"while (min_2 == min_1 && !timePoints_2.isEmpty()) {\r\n" + 
						"min_2 = timePoints_2.remove(0).getTime();\r\n" + 
						"}\r\n" + 
						"r = 0;\r\n" + 
						"if (min_2 < min_1) {\r\n" + 
						"r = min_1;\r\n" + 
						"min_1 = min_2;\r\n" + 
						"min_2 = r;\r\n" + 
						"}\r\n" + 
						"l = timePoints_2.toArray().length;\r\n" + 
						"for (int i = 0; i < l; i++) {\r\n" + 
						"float new_t = timePoints_2.remove(0).getTime();\r\n" + 
						"if (new_t < min_1) {\r\n" + 
						"min_2 = min_1;\r\n" + 
						"min_1 = new_t;\r\n" + 
						"} else if (new_t < min_2 && new_t != min_1) {\r\n" + 
						"min_2 = new_t;\r\n" + 
						"}\r\n" + 
						"}\r\n" + 
						"s = cloner.deepClone(s_1);\r\n" + 
						"while (!s.getMessageQueue().isEmpty()) {\r\n" + 
						"Message msg = s.getMessageQueue().remove();\r\n" + 
						"if (msg.getAfter_1() == min_1 && msg.getAfter_2() == min_1) {\r\n" + 
						"min_2 = min_1;\r\n" + 
						"break;\r\n" + 
						"}\r\n" + 
						"}\r\n" + 
						"System.out.println(\"min_1: \" + min_1);\r\n" + 
						"programRetValue += \"min_1: \" + min_1 + \"\\r\\n\";\r\n" +
						"System.out.println(\"min_2: \" + min_2);" +
						"programRetValue += \"min_2: \" + min_2 + \"\\r\\n\";\r\n" +
						"new_s.setState_time_1(min_1);\r\n" + 
						"new_s.setState_time_2(min_2);\r\n" + 
						"t_1 = min_1;\r\n" + 
						"t_2 = min_2;\r\n" + 
						"if (!contains(queue, new_s, mode) && !contains(queue_2, new_s, mode)) {\r\n" + 
						"queue.add(new_s);\r\n" + 
						"states_num += 1;\r\n" + 
						"System.out.println(printState(new_s, states_num));\r\n" + 
						"programRetValue += printState(new_s, states_num);\r\n" +
						"} else {\r\n" + 
						"System.out.println(\"equal:\\r\\n\" + printState(new_s, 0));\r\n" + 
						"programRetValue += \"equal:\\r\\n\" + printState(new_s, 0);\r\n" +
						"}\r\n}\r\n" +  
						"}\r\n" + "}\r\n" + "}\r\n" + "}\r\n";
		return retValue;
	}

	private String printMessageFunction() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "private static String printMessage(Message msg) {\r\n" + 
				"String retValue = \"\";\r\n" + 
				"retValue += \"MsgName:\" + msg.getMsgName() + \", \" + \"MsgSender:\" + msg.getSender() + \", \"\r\n" + 
				"+ \"MsgReceiver:\" + msg.getReceiver() + \", \" + \"MsgAfterIntervalBegin:\" + msg.getAfter_1() + \", \"\r\n" + 
				"+ \"MsgAfterIntervalEnd:\" + msg.getAfter_2() + \"\\r\\n\";\r\n" + 
				"return retValue;\r\n" + 
				"}\r\n";
		return retValue;
	}

	private String printStatesQueueFunction() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "private static String printStatesQueue(Queue<State> q) {\r\n" + 
				"String retValue = \"\";\r\n" + 
				"Iterator<State> itr = q.iterator();\r\n" + 
				"int i = 1;\r\n" + 
				"while (itr.hasNext()) {\r\n" + 
				"retValue += printState(itr.next(), i);\r\n" + 
				"i++;\r\n" + 
				"}\r\n" + 
				"DirectoryCreator directoryCreator = new DirectoryCreator();\r\n" + 
				"try {\r\n" + 
				"directoryCreator.addFile(\"a.txt\", retValue);\r\n" + 
				"} catch (IOException e) {\r\n" + 
				"// TODO Auto-generated catch block\r\n" + 
				"e.printStackTrace();\r\n" + 
				"}\r\n" + 
				"return retValue;\r\n" + 
				"}\r\n";
		return retValue;
	}

	private String printStateFunction() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "private static String printState(State s, int state_number) {\r\n" + 
				"String retValue = \"\";\r\n" + 
				"retValue += \"-------------------------------------------------------------------------\\r\\n\";\r\n" + 
				"retValue += \"State number: \" + state_number + \"\\r\\n\";\r\n" +
				"retValue += \"State begin time: \" + s.getState_time_1() + \", State end time: \" + s.getState_time_2() + \"\\r\\n\";\r\n"+
				"MessageQueue<Message> mq = s.getMessageQueue();\r\n" + 
				"Actors[] actors = s.getActors();\r\n" + 
				"Iterator<Message> itr = mq.iterator();\r\n" + 
				"retValue += \"messageQueue contents: \\r\\n\";\r\n" + 
				"while (itr.hasNext()) {\r\n" + 
				"Message msg = itr.next();\r\n" + 
				"if (msg != null) {\r\n" + 
				"retValue += \"MsgName:\" + msg.getMsgName() + \", \" + \"MsgSender:\" + msg.getSender() + \", \"\r\n" + 
				"+ \"MsgReceiver:\" + msg.getReceiver() + \", \" + \"MsgAfterIntervalBegin:\" + msg.getAfter_1() + \", \"\r\n" + 
				"+ \"MsgAfterIntervalEnd:\" + msg.getAfter_2() + \"\\r\\n\";\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"retValue += \"actors variables: \\r\\n\";\r\n" + 
				"\r\n" + 
				"for (int i = 0; i < actors.length; i++) {\r\n" + 
				"if (actors[i] != null) {\r\n";
		for (ReactiveClassDeclaration rc : rebecaModel.getRebecaCode().getReactiveClassDeclaration()) {
			retValue += "if (actors[i].getClass().getSimpleName().equals(\"" + rc.getName() + "\")) {\r\n" + 
					rc.getName() + " a = (" + rc.getName() + ") actors[i];\r\n" + 
					"retValue += \"Actor's Id:\" + a.getId() + \", class:\" + a.getClass().getSimpleName() + \", name:\" + a.getName() + \", \";\r\n";
			for (FieldDeclaration fd : rc.getStatevars()) {
				for (VariableDeclarator var : fd.getVariableDeclarators()) {
					retValue += "retValue += \"" + var.getVariableName() +  ":\" + a.get" + var.getVariableName() + "() + \", \";" + "\r\n";
				}
			}
			retValue += "retValue += \"\\r\\n\";\r\n" +
					"}\r\n";
		}
				 retValue += "}\r\n" + 
				 		"}\r\n" + 
				 		"retValue += \"-------------------------------------------------------------------------\" + \"\\r\\n\";\r\n" + 
				 		"DirectoryCreator directoryCreator = new DirectoryCreator();\r\n" + 
				 		"try {\r\n" + 
				 		"directoryCreator.addFile(\"a.txt\", retValue);\r\n" + 
				 		"} catch (IOException e) {\r\n" + 
				 		"// TODO Auto-generated catch block\r\n" + 
				 		"e.printStackTrace();\r\n" + 
				 		"}\r\n" + 
				 		"return retValue;\r\n" + 
				 		"}\r\n"; 
				
		return retValue;
	}

	private String containsFunction() {
		// TODO Auto-generated method stub
		String retValue = "private static boolean contains(Queue<State> queue, State s_1, String mode) {\r\n" + 
				"// TODO Auto-generated method stub\r\n" + 
				"Iterator i = queue.iterator();\r\n" + 
				"while (i.hasNext()) {\r\n" + 
				"State s = (State) i.next();\r\n" + 
				"if (s_1.equals(s, mode)) {\r\n" + 
				"System.out.println(\"equal_2: \" + printState(s_1, 0));\r\n" + 
				"System.out.println(\"equal_1: \" + printState(s, 0));\r\n" + 
				"return true;\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"return false;\r\n" + 
				"}\r\n";
		return retValue;
	}

	public String discreteTimeQueueManagement() {
		// TODO Auto-generated method stub
		String retValue = "if (mode.equals(\"d\")) {\r\n" +
				"while (!queue.isEmpty()) {\r\n" + 
				"State s_1 = new State();\r\n" + 
				"State s_2 = new State();\r\n" + 
				"s_1 = queue.poll();\r\n" + 
				"queue_2.add(s_1);\r\n" + 
				"State s = cloner.deepClone(s_1);\r\n" +
				"if (!s.getMessageQueue().isEmpty() && s.getMessageQueue().peek() != null) {\r\n" + 
				"float after = s.getMessageQueue().peek().getAfter_1();\r\n" + 
				"t_1 = after;\r\n" +
				"t_2 = t_1;\r\n" +
				"int highPriority_num = 0;\r\n" + 
				"MessageQueue<Message> mq_2 = new MessageQueue<Message>();\r\n" + 
				"while (!s.getMessageQueue().isEmpty() && s.getMessageQueue().peek().getAfter_1() == after) {\r\n" + 
				"mq_2.add(s.getMessageQueue().remove());\r\n" + 
				"highPriority_num += 1;\r\n" + 
				"}\r\n" + 
				"Message[] equalPriorityMsgs = new Message[highPriority_num];\r\n" + 
				"for (int i = 0; i < highPriority_num; i++) {\r\n" + 
				"equalPriorityMsgs[i] = mq_2.remove();\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"for (int i = 0; i < equalPriorityMsgs.length; i++) {\r\n" + 
				"State new_s = cloner.deepClone(s);\r\n" + 
				"MessageQueue<Message> mq_3 = new MessageQueue<Message>();\r\n" + 
				"for (int j = 0; j < equalPriorityMsgs.length; j++) {\r\n" + 
				"if (i != j) {\r\n" + 
				"new_s.getMessageQueue().add(equalPriorityMsgs[j]);\r\n" + 
				"} else {\r\n" + 
				"a = equalPriorityMsgs[j];\r\n" + 
				"}\r\n" + 
				"}\r\n";
		for (ReactiveClassDeclaration rc : rebecaModel.getRebecaCode().getReactiveClassDeclaration()) {
			for (MsgsrvDeclaration msgsrv : rc.getMsgsrvs()) {
				for (MainRebecDefinition md : rebecaModel.getRebecaCode().getMainDeclaration()
						.getMainRebecDefinition()) {
					ReactiveClassDeclaration metaData;
					try {
						metaData = TypesUtilities.getInstance().getMetaData(md.getType());
						if (rc.getName() == metaData.getName()) {
							retValue += "id = 0;\r\n" + 
									"for (int j = 0; j < actorsNames.length; j++) {\r\n" + 
									"if (actorsNames[j] != null && actorsNames[j].equals(\"" + md.getName() + "\")) {\r\n" + 
									"id = j;\r\n" + 
									"break;\r\n" + 
									"}\r\n" + 
									"}\r\n";
							retValue += "if (a.getReceiver().equals(\"" + md.getName()
									+ "\") && a.getMsgName().equals(\"" + msgsrv.getName() + "\")" + " && actors[id].getClass().getSimpleName().equals(\"" + metaData.getName() +"\")" + ") {\r\n"
									+ "s_2 = " + "(("+ metaData.getName() +") new_s.getActors()[id])" + "." + msgsrv.getName() + "(t_1, t_2, new_s, mode);\r\n" + "if (!contains(queue, s_2, mode) && !contains(queue_2, s_2, mode)) {\r\n" + 
											"queue.add(s_2);\r\n" + "states_num += 1;\r\n" + "System.out.println(printState(s_2, states_num));\r\n" + "programRetValue += printState(s_2, states_num);\r\n" +
											"}" + "else {\r\nSystem.out.println(\"equal:\\r\\n\" + printState(s_2, 0));\r\n" + "programRetValue += \"equal:\\r\\n\" + printState(s_2, 0);\r\n" + "}\r\n}\r\n";

						}

					} catch (CodeCompilationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		retValue += "}\r\n" + "}\r\n" + "else {\r\n" + 
				"queue.poll();\r\n" + 
				"}\r\n" + "n += 1;\r\n" + "}\r\n" + "}\r\n" ;
		return retValue;
	}

	private String defineRebecs() {
		// TODO Auto-generated method stub
		String retValue = "";
		for (MainRebecDefinition md : rebecaModel.getRebecaCode().getMainDeclaration().getMainRebecDefinition()) {
			try {
				ReactiveClassDeclaration metaData = TypesUtilities.getInstance().getMetaData(md.getType());
				retValue += metaData.getName() + " " + md.getName() + " = new " + metaData.getName() + "(\""
						+ md.getName() + "\", actorId, mq);" + NEW_LINE + "actors[actorId] = " + md.getName() +";\r\n";
				retValue += "actorsNames[actorId] = \"" + md.getName() + "\";\r\n";
				retValue += "actorId += 1;\r\n";
			} catch (CodeCompilationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return retValue;
	}

}
