package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.maven.artifact.repository.metadata.Metadata;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MainRebecDefinition;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.MsgsrvDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.OrdinaryPrimitiveType;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ReactiveClassDeclaration;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.RebecaModel;
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
		String retValue = "import java.util.*;\r\n";
		retValue += "public class Main {\r\n" + "public static void main(String[] args) throws CloneNotSupportedException {\r\n"
				+ "Queue<State> queue = new LinkedList<State>();\r\n"
				+ "MessageQueue<Message> mq = new MessageQueue<Message>();\r\n"
				+ "float t = 0;\r\n"
				+ "Message a = new Message();\r\n" + "Actors[] actors = new Actors[10];\r\n" + 
						"int actorId = 0;\r\n" + defineRebecs() + "State s_0 = new State();\r\n" + 
								"s_0.setActors(actors);\r\n" + 
								"s_0.setMessageQueue(mq);\r\n" + 
								"queue.add(s_0);\r\n"
				+ queueManagement() + "	\r\n" + "}\r\n" + containsFunction() + "}";
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
		String retValue = "Iterator i = queue.iterator();\r\n" + "while (i.hasNext()) {\r\n" + 
				"State s_1 = new State();\r\n" + 
				"State s_2 = new State();\r\n" + 
				"s_1 = (State) i.next();\r\n" + 
				"if (!s_1.getMessageQueue().isEmpty()) {\r\n" + 
				"float after = s_1.getMessageQueue().peek().getAfter();\r\n" + 
				"while (s_1.getMessageQueue().peek().getAfter() == after) {\r\n" + 
				"a = s_1.getMessageQueue().poll();\r\n";
		for (ReactiveClassDeclaration rc : rebecaModel.getRebecaCode().getReactiveClassDeclaration()) {
			for (MsgsrvDeclaration msgsrv : rc.getMsgsrvs()) {
				for (MainRebecDefinition md : rebecaModel.getRebecaCode().getMainDeclaration()
						.getMainRebecDefinition()) {
					ReactiveClassDeclaration metaData;
					try {
						metaData = TypesUtilities.getInstance().getMetaData(md.getType());
						if (rc.getName() == metaData.getName()) {
							retValue += "if (a.getReceiver().equals(\"" + md.getName()
									+ "\") && a.getMsgName().equals(\"" + msgsrv.getName() + "\")) {\r\n"
									+ "s_2 = " + md.getName() + "." + msgsrv.getName() + "(t, s_1);\r\n" + "if (!contains(queue, s_2)) {\r\n" + 
											"queue.add(s_2);\r\n" + 
											"}" + "}\r\n";

						}

					} catch (CodeCompilationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		retValue += "}\r\n" + "}\r\n" + "}";
		return retValue;
	}

	private String defineRebecs() {
		// TODO Auto-generated method stub
		String retValue = "";
		for (MainRebecDefinition md : rebecaModel.getRebecaCode().getMainDeclaration().getMainRebecDefinition()) {
			try {
				ReactiveClassDeclaration metaData = TypesUtilities.getInstance().getMetaData(md.getType());
				retValue += metaData.getName() + " " + md.getName() + " = new " + metaData.getName() + "(\""
						+ md.getName() + "\", actorId, mq);" + NEW_LINE + "actors[actorId] = " + md.getName() +";\r\n" + 
								"actorId += 1;\r\n";
			} catch (CodeCompilationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return retValue;
	}

}
