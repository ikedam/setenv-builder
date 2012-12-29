package jp.ikedam.jenkins.plugins.setenv_builder;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import java.io.Serializable;

import hudson.Extension;
import hudson.model.Descriptor;

import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

/**
 * テキストエリアに書いて環境変数を定義する。
 */
public class TextareaSetenvConfig extends AbstractSetenvConfig implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /**
     * ビルドパラメータの定義の際などに使用するビューの情報
     * resource フォルダ以下のクラス名に対応するフォルダから以下のファイルが使用される
     *    config.jelly
     *        ジョブ設定で、このモジュールが選択された場合に表示する追加の設定項目
     */
    @Extension
    public static class DescriptorImpl extends Descriptor<SetenvConfig>
    {
        /**
         * ビルドパラメータの追加時に表示される項目名
         */
        @Override
        public String getDisplayName()
        {
            return Messages.TextareaSetenvConfig_DisplayName();
        }
    }
    
    /**
     * 設定された変数指定を表示用に再構築する
     */
    public String getEnvText()
    {
        List <String> envDefList = new ArrayList<String>();
        for(SetenvEntry e: getSetenvEntryList()){
            envDefList.add(String.format("%s=%s", e.getName(), e.getValue()));
        }
        return StringUtils.join(envDefList, "\n");
    }
    
    /**
     * Jenkinsが画面入力からこのオブジェクトを作成するときに使用するコンストラクタ。
     * (設定から復元される時にはコンストラクタを使わずオブジェクトが直接復元される)
     */
    @DataBoundConstructor
    public TextareaSetenvConfig(String envText)
        throws IOException
    {
        Logger logger = Logger.getLogger(getClass().getName());
        List<SetenvEntry> setenvEntryList = new ArrayList<SetenvEntry>();
        List<String> lines = Arrays.asList(envText.split("\\r?\\n"));
        for(String line: lines){
            if(line.isEmpty())
            {
                continue;
            }
            int sep = line.indexOf('=');
            if(sep < 0)
            {
                logger.warning(String.format("Following line ignored: %s", line));
                continue;
            }
            String name = line.substring(0, sep);
            String value = line.substring(sep + 1);
            setenvEntryList.add(new SetenvEntry(name, value));
        }
        setSetenvEntryList(setenvEntryList);
    }
}
