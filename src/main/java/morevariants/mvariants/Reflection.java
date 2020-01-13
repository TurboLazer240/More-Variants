package morevariants.mvariants;

import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {
	
	public static <T> MethodHandle findMethod(Class<T> classs, String[] methodNames, Class<?>... methodTypes) {
		final Method method = ReflectionHelper.findMethod(classs, null, methodNames, methodTypes);
		try {
			return MethodHandles.lookup().unreflect(method);
		} catch (IllegalAccessException e) {
			throw new ReflectionHelper.UnableToFindMethodException(methodNames, e);
		}
	}

	public static MethodHandle findFieldGetter(Class<?> classs, String... fieldNames) {
		final Field field = ReflectionHelper.findField(classs, fieldNames);

		try {
			return MethodHandles.lookup().unreflectGetter(field);
		} catch (IllegalAccessException e) {
			throw new ReflectionHelper.UnableToAccessFieldException(fieldNames, e);
		}
	}

	public static MethodHandle findFieldSetter(Class<?> classs, String... fieldNames) {
		final Field field = ReflectionHelper.findField(classs, fieldNames);

		try {
			return MethodHandles.lookup().unreflectSetter(field);
		} catch (IllegalAccessException e) {
			throw new ReflectionHelper.UnableToAccessFieldException(fieldNames, e);
		}
	}
}