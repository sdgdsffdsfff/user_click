package cn.clickwise.clickad.classify_pattern;

import java.net.InetSocketAddress;
import java.util.Properties;


import com.sun.net.httpserver.HttpServer;

public class ClassifyPatternServer implements Runnable{
	
	private Properties properties = new Properties();
	
	@Override
	public void run() {
		
		// 配置成根据传入请求的前缀不同调用不同的处理程序
		// 每种请求对应一个handler
		
		try {
			HttpServer hs = HttpServer.create(
					new InetSocketAddress(Integer.parseInt(properties.getProperty("port"))), 0);

            CallMap callMap=ClassifierFactory.getCallMap();
            hs.createContext(callMap.method, callMap.handler);
            
			hs.setExecutor(null);
			hs.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void read_input_parameters(String[] args) {
		int i;
		for (i = 0; (i < args.length) && ((args[i].charAt(0)) == '-'); i++) {
			switch ((args[i].charAt(1))) {
			case 'h':
				print_help();
				System.exit(0);
			case 'p':
				i++;
				properties.setProperty("port", args[i]);
				break;
			default:
				System.out.println("Unrecognized option " + args[i] + "!");
				print_help();
				System.exit(0);
			}
		}

		System.out.println(properties.toString());
	}
	
	public static void print_help() {
		System.out.println("usage: AuxiliaryServer [options]");
		System.out.println("options: -h  -> this help");
		System.out.println("         -p  auxiliary server port");
	}
	
	public static void main(String[] args)
	{
		ClassifyPatternServer cps=new ClassifyPatternServer();
		cps.read_input_parameters(args);
		Thread serverThread = new Thread(cps);
		serverThread.start();	
	}

	
}
