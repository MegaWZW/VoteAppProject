package com.course.app.services.api;

import com.course.app.services.Result;

public interface IStatisticService {
	/**
	 * Метод для подсчёта результатов голосования
	 * @return объект Результат, содержащий информацию о результатах
	 *         голосования
	 */
	Result calculate();
}
