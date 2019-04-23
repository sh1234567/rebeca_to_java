package org.rebecalang.modeltransformer.java.timedrebeca;

import java.io.IOException;
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
				+ "Queue<State> queue = new LinkedList<State>();\r\n"
				+ "Queue<State> queue_2 = new LinkedList<State>();\r\n"
				+ "MessageQueue<Message> mq = new MessageQueue<Message>();\r\n"
				+ "Cloner cloner = new Cloner();\r\n"
				+ "float t = 0;\r\n"
				+ "int id = 0;\r\n"
				+ "int states_num = 0;\r\n"
				+ "Message a = new Message();\r\n" + "Actors[] actors = new Actors[10];\r\n" + "String[] actorsNames = new String[10];\r\n" +
						"int actorId = 0;\r\n" + defineRebecs() + "State s_0 = new State();\r\n" + 
								"s_0.setActors(actors);\r\n" + 
								"s_0.setMessageQueue(mq);\r\n" + 
								"queue.add(s_0);\r\n" + "states_num += 1;\r\n" +
								"System.out.println(printState(s_0));\r\n" + "int n = 0;\r\n"
				+ queueManagement() + "	\r\n" + "}\r\n" + containsFunction() + printStateFunction() + printStatesQueueFunction() + "}";
		return retValue;
	}

	private String printStatesQueueFunction() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "private static String printStatesQueue(Queue<State> q) {\r\n" + 
				"String retValue = \"\";\r\n" + 
				"Iterator<State> itr = q.iterator();\r\n" + 
				"while (itr.hasNext()) {\r\n" + 
				"retValue += printState(itr.next());\r\n" + 
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
		retValue += "private static String printState(State s) {\r\n" + 
				"String retValue = \"\";\r\n" + 
				"MessageQueue<Message> mq = s.getMessageQueue();\r\n" + 
				"Actors[] actors = s.getActors();\r\n" + 
				"Iterator<Message> itr = mq.iterator();\r\n" + 
				"retValue += \"messageQueue contents: \\r\\n\";\r\n" + 
				"while (itr.hasNext()) {\r\n" + 
				"Message msg = itr.next();\r\n" + 
				"if (msg != null) {\r\n" + 
				"retValue += \"MsgName:\" + msg.getMsgName() + \", \" + \"MsgSender:\" + msg.getSender() + \", \"\r\n" + 
				"+ \"MsgReceiver:\" + msg.getReceiver() + \", \" + \"MsgAfter:\" + msg.getAfter() + \"\\r\\n\";\r\n" + 
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
				 		"retValue += \"-------------------------------------------------------------------------\\r\\n\";\r\n" + 
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
		String retValue = "private static boolean contains(Queue<State> queue, State s_1) {\r\n" + 
				"// TODO Auto-generated method stub\r\n" + 
				"Iterator i = queue.iterator();\r\n" + 
				"while (i.hasNext()) {\r\n" + 
				"State s = (State) i.next();\r\n" + 
				"if (s_1.equals(s))\r\n" + 
				"return true;\r\n" + 
				"}\r\n" + 
				"return false;\r\n" + 
				"}\r\n";
		return retValue;
	}

	public String queueManagement() {
		// TODO Auto-generated method stub
		String retValue = "while (!queue.isEmpty()) {\r\n" + 
				"State s_1 = new State();\r\n" + 
				"State s_2 = new State();\r\n" + 
				"s_1 = queue.poll();\r\n" + 
				"queue_2.add(s_1);\r\n" + 
				"State s = cloner.deepClone(s_1);\r\n" +
				"if (!s.getMessageQueue().isEmpty() && s.getMessageQueue().peek() != null) {\r\n" + 
				"float after = s.getMessageQueue().peek().getAfter();\r\n" + 
				"t = after;\r\n" +
				"int highPriority_num = 0;\r\n" + 
				"MessageQueue<Message> mq_2 = new MessageQueue<Message>();\r\n" + 
				"while (!s.getMessageQueue().isEmpty() && s.getMessageQueue().peek().getAfter() == after) {\r\n" + 
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
				"}\r\n" + 
				"System.out.println(n + \" \" + a.getSender());\r\n";
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
									+ "s_2 = " + "(("+ metaData.getName() +") new_s.getActors()[id])" + "." + msgsrv.getName() + "(t, new_s);\r\n" + "if (!contains(queue, s_2) && !contains(queue_2, s_2)) {\r\n" + 
											"queue.add(s_2);\r\n" + "states_num += 1;\r\n" + "System.out.println(printState(s_2));\r\n" +
											"}" + "else System.out.println(\"equal\");\r\n" + "}\r\n";

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
				"}\r\n" + "n += 1;\r\n" + "}";
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
