package jp.ikedam.jenkins.plugins.setenv_builder;

import java.util.List;
import java.io.Serializable;

import hudson.Extension;
import hudson.model.Descriptor;

import org.kohsuke.stapler.DataBoundConstructor;

/**
 * リストでひとつずつ定義して環境変数を定義する。
 */
public class ListSetenvConfig extends AbstractSetenvConfig implements Serializable
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
            return Messages.ListSetenvConfig_DisplayName();
        }
    }
    
    /**
     * Jenkinsが画面入力からこのオブジェクトを作成するときに使用するコンストラクタ。
     * (設定から復元される時にはコンストラクタを使わずオブジェクトが直接復元される)
     */
    @DataBoundConstructor
    public ListSetenvConfig(List<SetenvEntry> setenvEntryList)
    {
        super(setenvEntryList);
    }
}
