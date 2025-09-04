package org.shift.FileFilterUtility;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.shift.FileFilterUtility.statistics.StatisticManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileFilterUtilityApplication implements CommandLineRunner {
	private final ArgsFilter argsFilter;

	public FileFilterUtilityApplication() {
		this.argsFilter = new ArgsFilter();
	}
	public static void main(String[] args) {
		SpringApplication.run(FileFilterUtilityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ArgsFilter argsFilter = new ArgsFilter();
		JCommander jCommander = JCommander.newBuilder()
				.addObject(argsFilter)
				.build();

		try {
			jCommander.parse(args);
			if (argsFilter.getInputFiles().isEmpty()) {
				throw new ParameterException("Необходимо указать обрабатываемые файлы при запуске утилиты.");
			}

			if (argsFilter.isShortStat() && argsFilter.isFullStat()) {
				throw new ParameterException("Необходимо выбрать только один вид статистики: -s или -f");
			}

		} catch (ParameterException e) {
			System.err.println("Ошибка в параметрах: " + e.getLocalizedMessage());
			jCommander.usage();
			System.exit(1);
		}

		try (FileWriterManager writerManager = new FileWriterManager(
				argsFilter.getOutputPath(),
				argsFilter.getPrefix(),
				argsFilter.isAppend())){

			StatisticManager statisticManager = new StatisticManager();
			FileFilterHandler handler = new FileFilterHandler(
					argsFilter.getInputFiles(),
					writerManager,
					statisticManager);

			handler.HandleFiles(argsFilter.isAppend());
			statisticManager.printReports(argsFilter.isFullStat());

		} catch (Exception e){
			System.err.println("Ошибка в работе утилиты: " + e.getLocalizedMessage());
			System.exit(1);
		}
	}
}
