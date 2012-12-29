package jp.ikedam.jenkins.plugins.setenv_builder;

import hudson.EnvVars;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SetenvEntryで変数の指定を管理するクラスの共通基底クラス
 */
public abstract class AbstractSetenvConfig extends SetenvConfig
{
    /**
     * 設定された変数指定
     */
    private List<SetenvEntry> setenvEntryList;
    
    public List<SetenvEntry> getSetenvEntryList()
    {
        return setenvEntryList;
    }

    public void setSetenvEntryList(List<SetenvEntry> setenvEntryList)
    {
        this.setenvEntryList = setenvEntryList;
    }

    /**
     * 追加で定義する変数のマップを返す。
     */
    public Map<String, String> getEnvMap(EnvVars env, PrintStream logger)
    {
        Map<String, String> envMap = new HashMap<String, String>();
        for(SetenvEntry e: getSetenvEntryList()){
            String key = e.getName();
            String value = env.expand(e.getValue());
            logger.println(String.format("Set Variable: %s=%s", key, value));
            envMap.put(key, value);
            env.put(key, value);
        }
        
        return envMap;
    }

    public AbstractSetenvConfig()
    {
        setSetenvEntryList(null);
    }

    public AbstractSetenvConfig(List<SetenvEntry> setenvEntryList)
    {
        setSetenvEntryList(setenvEntryList);
    }
}