package jp.ikedam.jenkins.plugins.setenv_builder;

import java.io.Serializable;
import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.AbstractDescribableImpl;

import org.kohsuke.stapler.DataBoundConstructor;

/**
 * 変数定義で使用する、変数名と値のペアを保存するモデル
 * TODO: パラメータチェック
 */
public class SetenvEntry extends AbstractDescribableImpl<SetenvEntry> implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /**
     * クラスに対するDescriptorをJenkinsに登録するためにExtensionを指定する。
     * また、resourcesフォルダ以下の、このクラスに対応するフォルダ内で以下のビューが使用される。
     *     config.jelly
     *         Jenkinsのジョブ設定での変数の指定のために使用される。
     */
    @Extension
    public static class DescriptorImpl extends Descriptor<SetenvEntry>
    {
        /**
         * この項目の名前。
         */
        @Override
        public String getDisplayName()
        {
            return Messages.SetenvEntry_DisplayName();
        }
    }
    
    /**
     * 定義する変数名
     */
    private String name = null;
    
    public String getName()
    {
        return name;
    }
    
    /**
     * 定義する値
     */
    private String value = null;
    
    public String getValue()
    {
        return value;
    }
    
    /**
     * Jenkinsが画面入力からこのオブジェクトを作成するときに使用するコンストラクタ。
     * (設定から復元される時にはコンストラクタを使わずオブジェクトが直接復元される)
     */
    @DataBoundConstructor
    public SetenvEntry(String name, String value)
    {
        this.name = name;
        this.value = value;
    }
}
