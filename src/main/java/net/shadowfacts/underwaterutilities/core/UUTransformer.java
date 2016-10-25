package net.shadowfacts.underwaterutilities.core;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Optional;

/**
 * @author shadowfacts
 */
public class UUTransformer implements IClassTransformer, Opcodes {

	@Override
	public byte[] transform(String name, String deobfName, byte[] bytes) {
		boolean obfuscated = !name.equals(deobfName);
		if (deobfName.equals("net.minecraft.entity.player.EntityPlayer")) {
			return transformEntity(bytes, obfuscated);
		}
		return bytes;
	}

	private static byte[] transformEntity(byte[] bytes, boolean obfuscated) {
		try {

			ClassNode node = new ClassNode();
			ClassReader reader = new ClassReader(bytes);
			reader.accept(node, 0);

			String IS_MOVED_BY_WATER = obfuscated ? "" : "isPushedByWater";

			Optional<MethodNode> method = node.methods.stream()
					.filter(it -> it.name.equals(IS_MOVED_BY_WATER))
					.findFirst();

			if (method.isPresent()) {

				InsnList instructions = new InsnList();

				instructions.add(new VarInsnNode(ALOAD, 0));
				instructions.add(new MethodInsnNode(INVOKESTATIC, "net/shadowfacts/underwaterutilities/core/ASMHooks", "isPushedByWater", "(Lnet/minecraft/entity/player/EntityPlayer;)Z", false));
				instructions.add(new InsnNode(IRETURN));

				method.get().instructions.clear();
				method.get().instructions.insert(instructions);

			} else {
				System.err.println("Could not find Entity.isPushedByWater");
			}

			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			node.accept(writer);
			return writer.toByteArray();

		} catch (Exception e) {
			System.err.println("Unable to transform net.minecraft.entity.Entity");
			e.printStackTrace();
			return bytes;
		}
	}

}
