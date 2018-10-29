package operate;

import java.io.IOException;
import java.util.Properties;

public class ExportSql {
    private static String getExportCommand(Properties properties) {
        StringBuffer command = new StringBuffer();
        String username = properties.getProperty("jdbc.username");  // 用户名
        String password = properties.getProperty("jdbc.password");  // 用户密码
        String exportDatabaseName = properties.getProperty("jdbc.exportDatabaseName"); // 需要导出的数据库名
        String host = properties.getProperty("jdbc.host"); // 从哪个主机导出数据库,如果没有指定这个值,则默认取localhost
        String port = properties.getProperty("jdbc.port"); // 使用的端口号
        String exportPath = properties.getProperty("jdbc.exportPath"); // 导出路径

        //注意哪些地方要空格,哪些不要空格
        command.append("mysqldump -u").append(username).append(" -p").append(password)//密码是用的小p,而端口是用的大P。
                .append(" -h").append(host).append(" -P").append(port).append(" ").append(exportDatabaseName).append(" -r ").append(exportPath);
        return command.toString();
    }

    /**
     * 根据属性文件的配置导出指定位置的指定数据库到指定位置
     * @param properties
     * @throws IOException
     */
    public static void export(Properties properties) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String command = getExportCommand(properties);
        runtime.exec(command);//这里简单一点异常我就直接往上抛
    }

}
