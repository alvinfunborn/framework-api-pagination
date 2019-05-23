package com.alvin.framework.api.pagination.method;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.List;

/**
 * datetime 2019/5/22 14:18
 *
 * @author sin5
 */
@SuppressWarnings("Duplicates")
public class ListMethodHandle<T> {

    private Object obj;
    private Class objClass;
    private Object[] args;
    private boolean virtual;

    private MethodHandle mh;

    public ListMethodHandle(Object obj, String methodName, boolean isVirtual, Object... args) {
        this.args = args;
        int length = args.length;
        Class[] argsClass = new Class[length];
        for (int i = 0; i < length; i++) {
            argsClass[i] = args[i].getClass();
        }
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(List.class, argsClass);
        if (obj instanceof Class) {
            this.objClass = (Class) obj;
            if (isVirtual) {
                this.virtual = true;
                try {
                    this.mh = lookup.findVirtual(objClass, methodName, mt);
                } catch (NoSuchMethodException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    this.mh = lookup.findStatic(objClass, methodName, mt);
                } catch (NoSuchMethodException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            this.virtual = true;
            this.obj = obj;
            this.objClass = obj.getClass();
            try {
                this.mh = lookup.findVirtual(objClass, methodName, mt);
            } catch (NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings({"unchecked", "JavaLangInvokeHandleSignature"})
    public List<T> invoke() {
        List<T> result = new ArrayList<>();
        try {
            List list;
            if (virtual) {
                int length = args.length;
                switch (length) {
                    case 0: list = (List) mh.invoke(obj); break;
                    case 1: list = (List) mh.invoke(obj, args[0]); break;
                    case 2: list = (List) mh.invoke(obj, args[0], args[1]); break;
                    case 3: list = (List) mh.invoke(obj, args[0], args[1], args[2]); break;
                    case 4: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3]); break;
                    case 5: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4]); break;
                    case 6: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5]); break;
                    case 7: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6]); break;
                    case 8: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]); break;
                    case 9: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[8], args[8]); break;
                    case 10: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[8], args[8], args[9]); break;
                    default: list = new ArrayList();
                }
            } else {
                list = (List) mh.invokeWithArguments(args);
            }
            for (Object o : list) {
                result.add((T) o);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings({"unchecked", "JavaLangInvokeHandleSignature"})
    public List<T> invoke(Object obj) {
        if (!virtual || obj.getClass() != objClass) {
            throw new IllegalArgumentException("caller must be object instanceof this.objClass");
        }
        List<T> result = new ArrayList<>();
        try {
            List list;
            int length = args.length;
            switch (length) {
                case 0: list = (List) mh.invoke(obj); break;
                case 1: list = (List) mh.invoke(obj, args[0]); break;
                case 2: list = (List) mh.invoke(obj, args[0], args[1]); break;
                case 3: list = (List) mh.invoke(obj, args[0], args[1], args[2]); break;
                case 4: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3]); break;
                case 5: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4]); break;
                case 6: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5]); break;
                case 7: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6]); break;
                case 8: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]); break;
                case 9: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[8], args[8]); break;
                case 10: list = (List) mh.invoke(obj, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[8], args[8], args[9]); break;
                default: list = new ArrayList();
            }
            for (Object o : list) {
                result.add((T) o);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
