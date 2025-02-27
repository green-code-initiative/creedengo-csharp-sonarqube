/*
 * creedengo - C# language - Provides rules to reduce the environmental footprint of your C# programs
 * Copyright © 2024 Green Code Initiative (https://green-code-initiative.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.greencodeinitiative.creedengo.csharp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarEdition;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.fest.assertions.Assertions.assertThat;

class CSharpPluginTest {

  @Test
  void getExtensions() {
    CSharpPlugin plugin = new CSharpPlugin();
    Plugin.Context context = new Plugin.Context(
        SonarRuntimeImpl.forSonarQube(Version.create(9, 9), SonarQubeSide.SCANNER,
            SonarEdition.COMMUNITY));
    plugin.define(context);

    @SuppressWarnings("rawtypes")
    List extensions = context.getExtensions();
    assertThat(extensions).hasSize(9);

    Class<?>[] expectedExtensions = new Class<?>[] {
        CSharpConfiguration.class,
        CSharpRulesDefinition.class
    };

    assertThat(nonProperties(extensions)).contains((Object[]) expectedExtensions);
    assertThat(extensions).hasSize(
        expectedExtensions.length
            + new CSharpPluginProperties(new CSharpConfiguration()).getProperties().size());
  }

  @Test
  void pico_container_key_differentiator() {
    assertThat(new CSharpPlugin().toString()).isEqualTo("creedengocsharp");
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private static List nonProperties(List extensions) {
    List props = new ArrayList<>();
    for (Object extension : extensions) {
      if (!(extension instanceof PropertyDefinition)) {
        props.add(extension);
      }
    }
    return Collections.unmodifiableList(props);
  }

}
