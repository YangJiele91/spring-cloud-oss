import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.oss.common.model.BaseEntity;

import java.util.Collections;

public class Generator {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/oss?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASS_WARD = "123456";

    private static final String APP_NAME = "user-service";
    private static final String PACK_NAME = "com.oss";
    private static final String MODULE_NAME = "user";
    private static final String TABLE_NAME = "user";
    private static final String TABLE_START = "";

    public Generator() {
    }

    public static void main(String[] args) {
        FastAutoGenerator.create(JDBC_URL, JDBC_USERNAME, JDBC_PASS_WARD)
                .globalConfig((builder) -> builder.author("sys")
                        .fileOverride()
                        .disableOpenDir()
                        .enableSwagger()
                        .outputDir(System.getProperty("user.dir") + "\\" + "user-service" + "\\src\\main\\java"))
                .packageConfig((builder) -> builder.parent(PACK_NAME)
                        .moduleName(MODULE_NAME)
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                System.getProperty("user.dir") + "\\" + APP_NAME + "\\src\\main\\java")))
                .templateConfig((builder) -> { // 注释会生成该类代码，放开注释忽略生成
                    /*builder.service("")
                            .mapper("")
                            .mapperXml("")
                            .serviceImpl("")
                            .controller("");*/
                })
                .strategyConfig((builder) -> builder.addInclude(TABLE_NAME)
                        .addTablePrefix(TABLE_START)
                        .entityBuilder()
                        .enableLombok()
                        .superClass(BaseEntity.class)
                        .addSuperEntityColumns("id", "create_time", "update_time")).
                execute();
    }
}
