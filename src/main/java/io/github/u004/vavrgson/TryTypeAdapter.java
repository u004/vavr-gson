/*
 * Copyright 2022 u004
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.u004.vavrgson;

import com.google.gson.*;
import io.vavr.control.Try;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A JSON type adapter for Vavr's {@link Try} type.
 */
@SuppressWarnings("unused")
final class TryTypeAdapter implements JsonDeserializer<Try<?>>, JsonSerializer<Try<?>> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Try<?> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		Object object = context.deserialize(json, ((ParameterizedType) type).getActualTypeArguments()[0]);

		if (object == null) {
			return Try.failure(new NullPointerException());
		}

		return Try.success(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JsonElement serialize(Try<?> src, Type type, JsonSerializationContext context) {
		if (src == null) {
			return null;
		}

		return context.serialize(src.getOrNull(), ((ParameterizedType) type).getActualTypeArguments()[0]);
	}
}
