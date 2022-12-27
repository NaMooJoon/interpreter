package edu.handong.csee.plt;

import org.apache.commons.cli.*;

public class Runner {

	static boolean printHelp = false; // for -h option
	static boolean onlyParser = false; // for -p option
	static boolean withLaziness = false; // for -l option
	static boolean concreteCode = false; // for -c option
	static String code = ""; // for -c option

	private App app;
	
	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.run(args);
	}
	private void run(String[] args) {
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (printHelp) {
				printHelp(options);
				return;
			}

			app = new App(onlyParser, withLaziness, code);
			app.start();
		}
	}
	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		options.addOption(Option.builder("p").longOpt("parser")
				.desc("The option that only enables a parser")
				.build());

		options.addOption(Option.builder("l").longOpt("laziness")
				.desc("The option that enables laziness")
				.build());

		options.addOption(Option.builder("c").longOpt("concrete")
				.desc("The option that passes the argument for a concrete code")
				.hasArg()
				.required()
				.argName("concrete code")
				.build());


		return options;
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			printHelp = cmd.hasOption("h");
			onlyParser = cmd.hasOption("p");
			withLaziness = cmd.hasOption("l");
			concreteCode = cmd.hasOption("c");
			code = cmd.getOptionValue("c");
		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}

	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "RBMRCFAE(supporting laziness) parser and interpreter by using java programming language";
		String footer = "\nPlease report issues at jjag1015@handong.ac.kr";
		formatter.printHelp("Interpreter", header, options, footer, true);
	}
}
