package jp.ikedam.jenkins.plugins.setenv_builder;

import java.util.Map;
import java.io.PrintStream;
import hudson.EnvVars;
import hudson.ExtensionPoint;
import hudson.DescriptorExtensionList;
import hudson.model.Hudson;
import hudson.model.Descriptor;
import hudson.model.AbstractDescribableImpl;

/**
 * 設定する変数のリストを返すモジュールの基底クラス。
 * 新規に選択を提供するモジュールを定義する場合、以下の手順で行う。
 * 1. ChoiceListProvider の派生クラスを定義する
 *     getChoiceList() の定義(オーバーライド)が必要
 * 2. 派生クラス内でDescriptorを定義する
 * 3. Descriptorに@Extensionをつける
 */
public abstract class SetenvConfig extends AbstractDescribableImpl<SetenvConfig> implements ExtensionPoint
{
    /**
     * 追加で定義する変数のマップを返す。
     */
    abstract public Map<String, String>  getEnvMap(EnvVars env, PrintStream logger);
    
    /**
     * 定義されているChoiceListProviderリストを返す。
     */
    static public DescriptorExtensionList<SetenvConfig,Descriptor<SetenvConfig>> all()
    {
        return Hudson.getInstance().<SetenvConfig,Descriptor<SetenvConfig>>getDescriptorList(SetenvConfig.class);
    }
}
